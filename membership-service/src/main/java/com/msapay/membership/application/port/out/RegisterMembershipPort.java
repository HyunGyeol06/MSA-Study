package com.msapay.membership.application.port.out;

import com.msapay.membership.domain.Membership;
import com.msapay.membership.adapter.out.persistence.MembershipJpaEntity;

public interface RegisterMembershipPort {

    MembershipJpaEntity createMembership(
            Membership.MembershipName membershipName,
            Membership.MembershipEmail membershipEmail,
            Membership.MembershipAddress membershipAddress,
            Membership.MembershipIsValid membershipIsValid,
            Membership.MembershipIsCorp membershipIsCorp
    );
}
