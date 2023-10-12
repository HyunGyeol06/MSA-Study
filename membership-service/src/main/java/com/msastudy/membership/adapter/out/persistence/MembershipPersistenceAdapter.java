package com.msastudy.membership.adapter.out.persistence;

import com.msastudy.membership.application.port.out.RegisterMembershipPort;
import com.msastudy.membership.common.PersistenceAdapter;
import com.msastudy.membership.domain.Membership;

@PersistenceAdapter
public class MembershipPersistenceAdapter implements RegisterMembershipPort {

    @Override
    public void createMembership(Membership.MembershipName membershipName, Membership.MembershipEmail membershipEmail, Membership.MembershipAddress membershipAddress, Membership.MembershipIsValid membershipIsValid, Membership.MembershipIsCorp membershipIsCorp) {

    }
}
