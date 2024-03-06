package com.msapay.banking.application.port.out;

import com.msapay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.msapay.banking.adapter.out.service.MembershipStatus;
import com.msapay.banking.domain.RegisteredBankAccount;

public interface GetMembershipPort {
    public MembershipStatus getMembership(String membershipId);
}
