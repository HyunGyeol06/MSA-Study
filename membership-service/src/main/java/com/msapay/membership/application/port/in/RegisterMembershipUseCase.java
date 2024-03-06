package com.msapay.membership.application.port.in;


import com.msapay.membership.domain.Membership;


public interface RegisterMembershipUseCase {
    Membership registerMembership(RegisterMembershipCommand command);
}
