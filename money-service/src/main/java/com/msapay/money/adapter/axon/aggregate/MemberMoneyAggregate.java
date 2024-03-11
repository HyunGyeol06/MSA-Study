package com.msapay.money.adapter.axon.aggregate;

import com.msapay.money.adapter.axon.command.IncreaseMemberMoneyCommand;
import com.msapay.money.adapter.axon.command.MemberMoneyCreatedCommand;
import com.msapay.money.adapter.axon.event.IncreaseMemberMoneyEvent;
import com.msapay.money.adapter.axon.event.MemberMoneyCreatedEvent;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateRoot;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate()
@Data
@Log4j2
public class MemberMoneyAggregate {

    @AggregateIdentifier
    private String id;

    private Long membershipId;

    private int balance;

    @CommandHandler
    public MemberMoneyAggregate(MemberMoneyCreatedCommand command) {
        log.info("MemberMoneyCreatedCommand Handler");

        apply(new MemberMoneyCreatedEvent(command.getMembershipId()));
    }

    @CommandHandler
    public String handle(@NotNull IncreaseMemberMoneyCommand command) {
        log.info("IncreaseMemberMoneyCommand Handler");
        id = command.getAggregateIdentifier();

        apply(new IncreaseMemberMoneyEvent(id, command.getMembershipId(), command.getAmount()));

        return id;
    }

    @EventSourcingHandler
    public void on(MemberMoneyCreatedEvent event) {
        log.info("MemberMoneyCreatedEvent Sourcing Handler");
        id = UUID.randomUUID().toString();
        membershipId = Long.parseLong(event.getMembershipId());
        balance = 0;
    }

    @EventSourcingHandler
    public void on(IncreaseMemberMoneyEvent event) {
        log.info("IncreaseMemberMoneyEvent Sourcing Handler");
        id = event.getAggregateIdentifier();
        membershipId = Long.parseLong(event.getTargetMembershipId());
        balance = event.getAmount();
    }

    public MemberMoneyAggregate() {
    }
}
