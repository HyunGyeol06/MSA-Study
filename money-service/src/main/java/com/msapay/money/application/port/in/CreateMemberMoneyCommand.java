package com.msapay.money.application.port.in;

import com.msapay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateMemberMoneyCommand extends SelfValidating<CreateMemberMoneyCommand> {

    @NotNull
    private final String membershipId;

    public CreateMemberMoneyCommand(@NotNull String membershipId) {
        this.membershipId = membershipId;

        this.validateSelf();
    }
}
