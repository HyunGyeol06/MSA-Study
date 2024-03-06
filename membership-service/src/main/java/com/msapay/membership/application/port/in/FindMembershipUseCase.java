package com.msapay.membership.application.port.in;

import com.msapay.membership.domain.Membership;

public interface FindMembershipUseCase {
    Membership findMembership(FindMembershipCommand command);
}
