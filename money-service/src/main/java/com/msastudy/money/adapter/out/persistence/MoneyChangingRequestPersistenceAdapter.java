package com.msastudy.money.adapter.out.persistence;

import com.msastudy.common.PersistenceAdapter;
import com.msastudy.money.application.port.out.IncreaseMoneyPort;
import com.msastudy.money.domain.MemberMoney;
import com.msastudy.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyChangingRequestPersistenceAdapter implements IncreaseMoneyPort {

    private final SpringDataMoneyChangingRepository moneyChangingRepository;

    private final SpringDataMemberMoneyRepository memberMoneyRepository;

    @Override
    public MoneyChangingRequestJpaEntity createMoneyChangingRequest(MoneyChangingRequest.TargetMemberShipId targetMemberShipId, MoneyChangingRequest.ChangingTypeValue changingTypeValue, MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount, MoneyChangingRequest.ChangingMoneyStatusValue changingMoneyStatusValue, MoneyChangingRequest.Uuid uuid) {
        return moneyChangingRepository.save(
                new MoneyChangingRequestJpaEntity(
                        targetMemberShipId.getTargetMemberShipId(),
                        changingTypeValue.getChangingType(),
                        changingMoneyAmount.getChangingMoneyAmount(),
                        new Timestamp(System.currentTimeMillis()),
                        changingMoneyStatusValue.getChangingMoneyStatus(),
                        uuid.getUuid()
                )
        );
    }

    @Override
    public MemberMoneyJpaEntity increaseMoney(MemberMoney.MembershipId membershipId, int increaseMoneyAmount) {
        MemberMoneyJpaEntity entity;
        try {
            List<MemberMoneyJpaEntity> entityList = memberMoneyRepository.findByMembershipId(Long.parseLong(membershipId.getMembershipId()));
            entity = entityList.get(0);

            entity.setBalance(entity.getBalance() + increaseMoneyAmount);
            return memberMoneyRepository.save(entity);
        } catch (Exception e) {
            entity = new MemberMoneyJpaEntity(
                    Long.parseLong(membershipId.getMembershipId()),
                    increaseMoneyAmount
            );
            memberMoneyRepository.save(entity);
            return entity;
        }


    }
}
