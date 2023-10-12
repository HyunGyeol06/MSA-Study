package com.msastudy.membership.application.port.in;


import com.msastudy.membership.common.UseCase;
import com.msastudy.membership.domain.Membership;

@UseCase
public interface RegisterMembershipUseCase {
    Membership registerMembership(RegisterMembershipCommand command);
}
