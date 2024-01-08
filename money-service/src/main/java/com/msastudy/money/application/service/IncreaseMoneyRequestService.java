package com.msastudy.money.application.service;

import com.msastudy.common.UseCase;
import com.msastudy.money.adapter.in.web.MoneyChangingResultDetailMapper;
import com.msastudy.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.msastudy.money.adapter.out.persistence.MoneyChangingRequestMapper;
import com.msastudy.money.application.port.in.IncreaseMoneyRequestCommand;
import com.msastudy.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.msastudy.money.application.port.out.IncreaseMoneyPort;
import com.msastudy.money.domain.MemberMoney;
import com.msastudy.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@UseCase
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase {


    private final IncreaseMoneyPort increaseMoneyPort;

    private final MoneyChangingRequestMapper mapper;

    @Override
    public MoneyChangingRequest registerBankAccount(IncreaseMoneyRequestCommand command) {

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
}
