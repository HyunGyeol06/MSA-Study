package com.msastudy.membership.application.port.in;


import com.msastudy.membership.common.UseCase;
import com.msastudy.membership.domain.Membership;


public interface RegisterMembershipUseCase {
    Membership registerMembership(RegisterMembershipCommand command);
}
