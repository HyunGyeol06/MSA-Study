package com.msapay.membership.application.port.in;

import com.msapay.membership.domain.Membership;

import java.util.List;

public interface FindMembershipUseCase {
    Membership findMembership(FindMembershipCommand command);

    List<Membership> findMembershipListByAddress(FindMembershipListByAddressCommand command);
}
