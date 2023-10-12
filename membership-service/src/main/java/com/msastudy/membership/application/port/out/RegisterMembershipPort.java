package com.msastudy.membership.application.port.out;

import com.msastudy.membership.domain.Membership;

public interface RegisterMembershipPort {

    void createMembership(
            Membership.MembershipName membershipName,
            Membership.MembershipEmail membershipEmail,
            Membership.MembershipAddress membershipAddress,
            Membership.MembershipIsValid membershipIsValid,
            Membership.MembershipIsCorp membershipIsCorp
    );
}
