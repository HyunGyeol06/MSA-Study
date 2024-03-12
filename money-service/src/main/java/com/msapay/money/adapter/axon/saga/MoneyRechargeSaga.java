package com.msapay.money.adapter.axon.saga;

import com.msapay.common.event.*;
import com.msapay.money.adapter.axon.event.RechargingRequestCreatedEvent;
import com.msapay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.msapay.money.application.port.out.IncreaseMoneyPort;
import com.msapay.money.domain.MemberMoney;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
@NoArgsConstructor
@Log4j2
public class MoneyRechargeSaga {

    @NonNull
    private transient CommandGateway commandGateway;

    @Autowired
    public void setCommandGateway(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "rechargingRequestId")
    public void handle(RechargingRequestCreatedEvent event){
        log.info("RechargingRequestCreatedEvent Start saga");

        String checkRegisteredBankAccountId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith("checkRegisteredBankAccountId", checkRegisteredBankAccountId);



        // 기본적으로 axon framework 에서, 모든 aggregate 의 변경은, aggregate 단위로 되어야만 한다.
        commandGateway.send(new CheckRegisteredBankAccountCommand(
                        event.getRegisteredBankAccountAggregateIdentifier(),
                        event.getRechargingRequestId(),
                        event.getMembershipId(),
                        checkRegisteredBankAccountId,
                        event.getBankName(),
                        event.getBankAccountNumber(),
                        event.getAmount()
                )
        ).whenComplete(
                (result, throwable) -> {
                    if (throwable != null) {
                        throwable.printStackTrace();
                        log.info("CheckRegisteredBankAccountCommand Command failed");
                    } else {
                        log.info("CheckRegisteredBankAccountCommand Command success");
                    }
                }
        );
    }

    @SagaEventHandler(associationProperty = "checkRegisteredBankAccountId")
    public void handle(CheckedRegisteredBankAccountEvent event) {
        log.info("CheckedRegisteredBankAccountEvent saga: " + event.toString());
        boolean status = event.isChecked();
        if (status) {
            log.info("CheckedRegisteredBankAccountEvent event success");
        } else {
            log.info("CheckedRegisteredBankAccountEvent event Failed");
        }

        String requestFirmbankingId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith("requestFirmbankingId", requestFirmbankingId);

        // 송금 요청
        // 고객 계좌 -> 법인 계좌
        commandGateway.send(new RequestFirmbankingCommand(
                requestFirmbankingId,
                event.getFirmbankingRequestAggregateIdentifier()
                , event.getRechargingRequestId()
                , event.getMembershipId()
                , event.getFromBankName()
                , event.getFromBankAccountNumber()
                , "fastcampus"
                , "123456789"
                , event.getAmount()
        )).whenComplete(
                (result, throwable) -> {
                    if (throwable != null) {
                        throwable.printStackTrace();
                        log.info("RequestFirmbankingCommand Command failed");
                    } else {
                        log.info("RequestFirmbankingCommand Command success");
                    }
                }
        );
    }

    @SagaEventHandler(associationProperty = "requestFirmbankingId")
    public void handle(RequestFirmbankingFinishedEvent event, IncreaseMoneyPort increaseMoneyPort) {
        log.info("RequestFirmbankingFinishedEvent saga: " + event.toString());
        boolean status = event.getStatus() == 0;
        if (status) {
            log.info("RequestFirmbankingFinishedEvent event success");
        } else {
            log.info("RequestFirmbankingFinishedEvent event Failed");
        }

        // DB Update 명령.
        MemberMoneyJpaEntity resultEntity =
                increaseMoneyPort.increaseMoney(
                        new MemberMoney.MembershipId(event.getMembershipId())
                        , event.getMoneyAmount()
                );

        if (resultEntity == null) {
            // 실패 시, 롤백 이벤트
            String rollbackFirmbankingId = UUID.randomUUID().toString();
            SagaLifecycle.associateWith("rollbackFirmbankingId", rollbackFirmbankingId);
            commandGateway.send(new RollbackFirmbankingRequestCommand(
                    rollbackFirmbankingId
                    ,event.getRequestFirmbankingAggregateIdentifier()
                    , event.getRechargingRequestId()
                    , event.getMembershipId()
                    , event.getToBankName()
                    , event.getToBankAccountNumber()
                    , event.getMoneyAmount()
            )).whenComplete(
                    (result, throwable) -> {
                        if (throwable != null) {
                            throwable.printStackTrace();
                            log.info("RollbackFirmbankingRequestCommand Command failed");
                        } else {
                            log.info("Saga success : "+ result.toString());
                            SagaLifecycle.end();
                        }
                    }
            );
        } else {
            // 성공 시, saga 종료.
            SagaLifecycle.end();
        }
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "rollbackFirmbankingId")
    public void handle(RollbackFirmbankingFinishedEvent event) {
        log.info("RollbackFirmbankingFinishedEvent saga: " + event.toString());
    }
}
