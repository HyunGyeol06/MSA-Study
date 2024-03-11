package com.msapay.money.adapter.axon.event;

import com.msapay.common.SelfValidating;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class IncreaseMemberMoneyEvent extends SelfValidating<IncreaseMemberMoneyEvent> {

    private String aggregateIdentifier;

    private String targetMembershipId;

    private int amount;

    public IncreaseMemberMoneyEvent(String aggregateIdentifier, String targetMembershipId, int amount) {
        this.aggregateIdentifier = aggregateIdentifier;
        this.targetMembershipId = targetMembershipId;
        this.amount = amount;
    }

    public IncreaseMemberMoneyEvent() {
    }
}
