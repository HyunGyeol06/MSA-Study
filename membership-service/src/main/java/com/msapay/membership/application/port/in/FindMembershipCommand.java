package com.msapay.membership.application.port.in;

import com.msapay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class FindMembershipCommand extends SelfValidating<FindMembershipCommand> {
    private final String membershipId;
}
