package com.msapay.membership.application.port.out;


import com.msapay.membership.domain.Membership;
import com.msapay.membership.adapter.out.persistence.MembershipJpaEntity;

import java.util.List;

public interface FindMembershipPort {
    MembershipJpaEntity findMembership(
            Membership.MembershipId membershipId
    );

    List<MembershipJpaEntity> findMembershipListByAddress(
            Membership.MembershipAddress membershipAddress
    );
}
