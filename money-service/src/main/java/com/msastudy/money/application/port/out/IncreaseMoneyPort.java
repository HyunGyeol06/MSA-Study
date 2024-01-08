package com.msastudy.money.application.port.out;


import com.msastudy.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.msastudy.money.adapter.out.persistence.MoneyChangingRequestJpaEntity;
import com.msastudy.money.domain.MemberMoney;
import com.msastudy.money.domain.MoneyChangingRequest;

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
