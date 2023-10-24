package com.msastudy.banking.application.port.out;

import com.msastudy.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.msastudy.banking.domain.Membership;

public interface RegisterMembershipPort {

    RegisteredBankAccountJpaEntity createMembership(
            Membership.MembershipName membershipName,
            Membership.MembershipEmail membershipEmail,
            Membership.MembershipAddress membershipAddress,
            Membership.MembershipIsValid membershipIsValid,
            Membership.MembershipIsCorp membershipIsCorp
    );
}
