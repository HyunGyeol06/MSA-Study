package com.msapay.money.application.port.in;

import com.msapay.money.domain.MemberMoney;

public interface CreateMemberPort {
    void createMemberMoney(
            MemberMoney.MembershipId membershipId,
            MemberMoney.MoneyAggregateIdentifier aggregateIdentifier
    );

}
