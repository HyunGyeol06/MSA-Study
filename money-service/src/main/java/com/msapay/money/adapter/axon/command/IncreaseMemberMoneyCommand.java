package com.msapay.money.adapter.axon.command;

import com.msapay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class IncreaseMemberMoneyCommand extends SelfValidating<IncreaseMemberMoneyCommand> {

    @NotNull
    @TargetAggregateIdentifier
    private String aggregateIdentifier;

    @NotNull
    private final String membershipId;

    @NotNull
    private final int amount;

}
