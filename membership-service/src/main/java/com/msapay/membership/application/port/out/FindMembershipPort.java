package com.msapay.membership.application.port.out;


import com.msapay.membership.domain.Membership;
import com.msapay.membership.adapter.out.persistence.MembershipJpaEntity;

public interface FindMembershipPort {
    MembershipJpaEntity findMembership(
            Membership.MembershipId membershipId
    );
}
