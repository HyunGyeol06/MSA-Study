package com.msapay.membership.application.port.in;

import com.msapay.membership.domain.Membership;

public interface ModifyMembershipUseCase {
    Membership modifyMembership(ModifyMembershipCommand command);
}
