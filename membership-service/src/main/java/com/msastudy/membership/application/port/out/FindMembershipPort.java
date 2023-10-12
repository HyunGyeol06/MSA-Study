package com.msastudy.membership.application.port.out;


import com.msastudy.membership.adapter.out.persistence.MembershipJpaEntity;
import com.msastudy.membership.domain.Membership;

public interface FindMembershipPort {
    MembershipJpaEntity findMembership(
            Membership.MembershipId membershipId
    );
}
