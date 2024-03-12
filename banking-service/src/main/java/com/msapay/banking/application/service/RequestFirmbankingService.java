package com.msapay.banking.application.service;

import com.msapay.banking.adapter.axon.command.CreateFirmbankingRequestCommand;
import com.msapay.banking.adapter.axon.command.UpdateFirmbankingRequestCommand;
import com.msapay.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.msapay.banking.adapter.out.external.bank.FirmbankingResult;
import com.msapay.banking.application.port.in.UpdateFirmbankingCommand;
import com.msapay.banking.application.port.in.UpdateFirmbankingUsecase;
import com.msapay.banking.domain.FirmbankingRequest;
import com.msapay.banking.adapter.out.persistence.FirmbankingRequestJpaEntity;
import com.msapay.banking.adapter.out.persistence.FirmbankingRequestMapper;
import com.msapay.banking.application.port.in.RequestFirmbankingCommand;
import com.msapay.banking.application.port.in.RequestFirmbankingUsecase;
import com.msapay.banking.application.port.out.RequestExternalFirmbankingPort;
import com.msapay.banking.application.port.out.RequestFirmbankingPort;
import com.msapay.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.gateway.CommandGateway;

import javax.transaction.Transactional;
import java.util.UUID;

@UseCase
@RequiredArgsConstructor
@Transactional
@Log4j2
public class RequestFirmbankingService implements RequestFirmbankingUsecase, UpdateFirmbankingUsecase {

    private final RequestFirmbankingPort requestFirmbankingPort;

    private final FirmbankingRequestMapper mapper;

    private final RequestExternalFirmbankingPort requestExternalFirmbankingPort;

    private final CommandGateway commandGateway;

    @Override
    public FirmbankingRequest requestFirmbanking(RequestFirmbankingCommand command) {

        FirmbankingRequestJpaEntity requestedEntity = requestFirmbankingPort.createFirmbankingRequest(
                new FirmbankingRequest.FromBankName(command.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(command.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAccount(command.getMoneyAmount()),
                new FirmbankingRequest.FirmbankingStatus(0),
                new FirmbankingRequest.FirmbankingAggregateIdentifier("")
        );

        FirmbankingResult result = requestExternalFirmbankingPort.requestExternalFirmbanking(new ExternalFirmbankingRequest(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAmount()
        ));

        UUID randomUUID = UUID.randomUUID();
        requestedEntity.setUuid(randomUUID);

        if (result.getResultCode() == 0) {
            //성공
            requestedEntity.setFirmbankingStatus(1);
        } else {
            //실패
            requestedEntity.setFirmbankingStatus(2);
        }



        return mapper.mapToDomainEntity(requestFirmbankingPort.modifyFirmbankingRequest(requestedEntity), randomUUID);
    }

    @Override
    public void requestFirmbankingByEvent(RequestFirmbankingCommand command) {
        CreateFirmbankingRequestCommand axonCommand = CreateFirmbankingRequestCommand.builder()
                .toBankName(command.getToBankName())
                .toBankAccountNumber(command.getToBankAccountNumber())
                .fromBankName(command.getFromBankName())
                .fromBankAccountNumber(command.getFromBankAccountNumber())
                .moneyAmount(command.getMoneyAmount())
                .build();

        commandGateway.send(axonCommand).whenComplete(
                (result, throwable) -> {
                    if (throwable != null) {
                        log.warn("Warn: ", throwable);
                        throw new RuntimeException(throwable);
                    } else {
                        log.info("CreateFirmbankingRequestCommand completed");
                        log.info("Aggregate ID: " + result.toString());

                        FirmbankingRequestJpaEntity requestedEntity = requestFirmbankingPort.createFirmbankingRequest(
                                new FirmbankingRequest.FromBankName(command.getFromBankName()),
                                new FirmbankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                                new FirmbankingRequest.ToBankName(command.getToBankName()),
                                new FirmbankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                                new FirmbankingRequest.MoneyAccount(command.getMoneyAmount()),
                                new FirmbankingRequest.FirmbankingStatus(0),
                                new FirmbankingRequest.FirmbankingAggregateIdentifier(result.toString())
                        );

                        FirmbankingResult firmbankingResult = requestExternalFirmbankingPort.requestExternalFirmbanking(new ExternalFirmbankingRequest(
                                command.getFromBankName(),
                                command.getFromBankAccountNumber(),
                                command.getToBankName(),
                                command.getToBankAccountNumber(),
                                command.getMoneyAmount()
                        ));

                        if (firmbankingResult.getResultCode() == 0) {
                            //성공
                            requestedEntity.setFirmbankingStatus(1);
                        } else {
                            //실패
                            requestedEntity.setFirmbankingStatus(2);
                        }


                    }
                }
        );

    }

    @Override
    public void requestFirmbankingByEvent(UpdateFirmbankingCommand command) {
        UpdateFirmbankingRequestCommand axonCommand = new UpdateFirmbankingRequestCommand(
                command.getFirmbankingAggregateIdentifier(),
                command.getFirmbaningStatus()
        );

        commandGateway.send(axonCommand).whenComplete(
                (result, throwable) -> {
                    if (throwable != null) {
                        log.warn("Warn: ", throwable);
                    } else {
                        log.info("UpdateFirmbankingCommand completed");
                        log.info("Aggregate ID: " + result.toString());
                        FirmbankingRequestJpaEntity entity = requestFirmbankingPort.getFirmbankingRequest(
                            new FirmbankingRequest.FirmbankingAggregateIdentifier(command.getFirmbankingAggregateIdentifier())
                        );
                        entity.setFirmbankingStatus(command.getFirmbaningStatus());
                        requestFirmbankingPort.modifyFirmbankingRequest(entity);

                    }
                }
        );

    }
}
