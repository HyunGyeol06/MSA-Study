package com.msapay.money.application.service;

import com.msapay.common.CountDownLatchManager;
import com.msapay.common.RechargingMoneyTask;
import com.msapay.common.SubTask;
import com.msapay.money.adapter.axon.command.MemberMoneyCreatedCommand;
import com.msapay.money.adapter.in.kafka.RechargingMoneyResultConsumer;
import com.msapay.money.adapter.out.persistence.MoneyChangingRequestMapper;
import com.msapay.money.application.port.in.CreateMemberMoneyCommand;
import com.msapay.money.application.port.in.CreateMemberMoneyUseCase;
import com.msapay.money.application.port.in.IncreaseMoneyRequestCommand;
import com.msapay.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.msapay.common.UseCase;
import com.msapay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.msapay.money.application.port.out.GetMembershipPort;
import com.msapay.money.application.port.out.IncreaseMoneyPort;
import com.msapay.money.application.port.out.SendRechargingMoneyTaskPort;
import com.msapay.money.domain.MemberMoney;
import com.msapay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@UseCase
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase, CreateMemberMoneyUseCase {

    private final CountDownLatchManager countDownLatchManager;

    private final SendRechargingMoneyTaskPort sendRechargingMoneyTaskPort;

    private final GetMembershipPort membershipPort;

    private final IncreaseMoneyPort increaseMoneyPort;

    private final MoneyChangingRequestMapper mapper;

    private final CommandGateway commandGateway;

    @Override
    public MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command) {

        membershipPort.getMembership(command.getTargetMembershipId());


        MemberMoneyJpaEntity memberMoneyJpaEntity = increaseMoneyPort.increaseMoney(
                new MemberMoney.MembershipId(command.getTargetMembershipId()),
                command.getAmount()
        );

        if (memberMoneyJpaEntity != null) {
            return mapper.mapToDomainEntity(increaseMoneyPort.createMoneyChangingRequest(
                    new MoneyChangingRequest.TargetMemberShipId(command.getTargetMembershipId()),
                    new MoneyChangingRequest.ChangingTypeValue(1),
                    new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                    new MoneyChangingRequest.ChangingMoneyStatusValue(1),
                    new MoneyChangingRequest.Uuid(UUID.randomUUID())
                )
            );
        } else {
            return null;
        }
    }

    @Override
    public MoneyChangingRequest increaseMoneyRequestAsync(IncreaseMoneyRequestCommand command) {

        SubTask validMemberTask = SubTask.builder()
                .subTaskName("validMemberTask: "+"멤버십 유효성 검사")
                .membershipID(command.getTargetMembershipId())
                .taskType("membership")
                .status("ready")
                .build();

        SubTask validBankTask = SubTask.builder()
                .subTaskName("validMemberTask: "+"뱅킹 계좌 유효성 검사")
                .membershipID(command.getTargetMembershipId())
                .taskType("banking")
                .status("ready")
                .build();


        List<SubTask> subTaskList = new ArrayList<>();
        subTaskList.add(validMemberTask);
        subTaskList.add(validBankTask);

        RechargingMoneyTask task = RechargingMoneyTask.builder()
                .taskID(UUID.randomUUID().toString())
                .taskName("Increase Money Task / 머니 충전 Task")
                .subTaskList(subTaskList)
                .moneyAmount(command.getAmount())
                .membershipID(command.getTargetMembershipId())
                .toBankName("msapay")
                .build();


        sendRechargingMoneyTaskPort.sendRechargingMoneyTaskPort(task);
        countDownLatchManager.addCountDownLatch(task.getTaskID());

        try {
            countDownLatchManager.getCountDownLatch(task.getTaskID()).await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String result = countDownLatchManager.getDataForKey(task.getTaskID());

        if (result.equals("success")) {
            MemberMoneyJpaEntity memberMoneyJpaEntity = increaseMoneyPort.increaseMoney(
                    new MemberMoney.MembershipId(command.getTargetMembershipId()),
                    command.getAmount()
            );

            if (memberMoneyJpaEntity != null) {
                return mapper.mapToDomainEntity(increaseMoneyPort.createMoneyChangingRequest(
                                new MoneyChangingRequest.TargetMemberShipId(command.getTargetMembershipId()),
                                new MoneyChangingRequest.ChangingTypeValue(1),
                                new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                                new MoneyChangingRequest.ChangingMoneyStatusValue(1),
                                new MoneyChangingRequest.Uuid(UUID.randomUUID())
                        )
                );
            }
        } else {
            return null;
        }

        return null;
    }


    @Override
    public void createMemberMoney(CreateMemberMoneyCommand command) {
        MemberMoneyCreatedCommand axonCommand = new MemberMoneyCreatedCommand(command.getMembershipId());

        commandGateway.send(command);


    }
}
