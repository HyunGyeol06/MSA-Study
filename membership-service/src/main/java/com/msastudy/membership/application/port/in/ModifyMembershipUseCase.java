package com.msastudy.membership.application.port.in;

import com.msastudy.membership.adapter.in.web.ModifyMembershipRequest;
import com.msastudy.membership.domain.Membership;

public interface ModifyMembershipUseCase {
    Membership modifyMembership(ModifyMembershipCommand command);
}
