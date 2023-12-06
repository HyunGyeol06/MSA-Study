package com.msastudy.money.application.port.in;

import com.msastudy.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class IncreaseMoneyRequestCommand extends SelfValidating<IncreaseMoneyRequestCommand> {

    @NotNull
    private final String membershipId;

    @NotNull
    private final int amount;

    public IncreaseMoneyRequestCommand(String membershipId, int amount) {
        this.membershipId = membershipId;
        this.amount = amount;

        this.validateSelf();
    }
}
