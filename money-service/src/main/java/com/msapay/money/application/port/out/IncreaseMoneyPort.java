package com.msapay.money.application.port.out;


import com.msapay.money.adapter.out.persistence.MoneyChangingRequestJpaEntity;
import com.msapay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.msapay.money.domain.MemberMoney;
import com.msapay.money.domain.MoneyChangingRequest;

public interface IncreaseMoneyPort {

    MoneyChangingRequestJpaEntity createMoneyChangingRequest(
            MoneyChangingRequest.TargetMemberShipId targetMemberShipId,
            MoneyChangingRequest.ChangingTypeValue changingTypeValue,
            MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
            MoneyChangingRequest.ChangingMoneyStatusValue changingMoneyStatusValue,
            MoneyChangingRequest.Uuid uuid
    );

    MemberMoneyJpaEntity increaseMoney(
            MemberMoney.MembershipId membershipId,
            int increaseMoneyAmount
    );
}
