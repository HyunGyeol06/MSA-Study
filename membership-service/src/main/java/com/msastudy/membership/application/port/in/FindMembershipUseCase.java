package com.msastudy.membership.application.port.in;

import com.msastudy.membership.domain.Membership;

public interface FindMembershipUseCase {
    Membership findMembership(FindMembershipCommand command);
}
