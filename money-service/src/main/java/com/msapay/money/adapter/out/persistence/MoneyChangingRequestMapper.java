package com.msapay.money.adapter.out.persistence;

import com.msapay.money.domain.MoneyChangingRequest;
import org.springframework.stereotype.Component;

@Component
public class MoneyChangingRequestMapper {
    public MoneyChangingRequest mapToDomainEntity(MoneyChangingRequestJpaEntity moneyChangingRequestJpaEntity) {
        return MoneyChangingRequest.generateMoneyChangingRequest(
                new MoneyChangingRequest.MoneyChangingRequestId(moneyChangingRequestJpaEntity.getTargetMembershipId()),
                new MoneyChangingRequest.TargetMemberShipId(moneyChangingRequestJpaEntity.getTargetMembershipId()),
                new MoneyChangingRequest.ChangingTypeValue(moneyChangingRequestJpaEntity.getMoneyChangingType()),
                new MoneyChangingRequest.ChangingMoneyAmount(moneyChangingRequestJpaEntity.getMoneyAmount()),
                new MoneyChangingRequest.ChangingMoneyStatusValue(moneyChangingRequestJpaEntity.getChangingMoneyStatus()),
                new MoneyChangingRequest.Uuid(moneyChangingRequestJpaEntity.getUuid())
        );
    }
}
