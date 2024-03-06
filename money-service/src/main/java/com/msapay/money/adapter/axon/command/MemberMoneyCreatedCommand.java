package com.msapay.money.adapter.axon.command;

import com.msapay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberMoneyCreatedCommand extends SelfValidating<MemberMoneyCreatedCommand> {

    @NotNull
    private final String membershipId;

    public MemberMoneyCreatedCommand(@NotNull String membershipId) {
        this.membershipId = membershipId;

        this.validateSelf();
    }
}
