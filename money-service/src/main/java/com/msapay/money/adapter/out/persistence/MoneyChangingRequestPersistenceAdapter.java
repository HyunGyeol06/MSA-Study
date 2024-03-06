package com.msapay.money.adapter.out.persistence;

import com.msapay.common.PersistenceAdapter;
import com.msapay.money.application.port.out.IncreaseMoneyPort;
import com.msapay.money.domain.MemberMoney;
import com.msapay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

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
