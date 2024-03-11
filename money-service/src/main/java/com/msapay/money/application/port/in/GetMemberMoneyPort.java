package com.msapay.money.application.port.in;

import com.msapay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.msapay.money.domain.MemberMoney;

public interface GetMemberMoneyPort {
    MemberMoneyJpaEntity getMemberMoney(
            MemberMoney.MembershipId membershipId
    );
}
