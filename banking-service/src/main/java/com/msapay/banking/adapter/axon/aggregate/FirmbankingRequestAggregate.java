package com.msapay.banking.adapter.axon.aggregate;

import com.msapay.banking.adapter.axon.command.CreateFirmbankingRequestCommand;
import com.msapay.banking.adapter.axon.command.UpdateFirmbankingRequestCommand;
import com.msapay.banking.adapter.axon.event.FirmbankingCreatedRequestEvent;
import com.msapay.banking.adapter.axon.event.UpdateFirmbankingRequestEvent;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate()
@Data
@Log4j2
public class FirmbankingRequestAggregate {

    @AggregateIdentifier
    private String id;

    private String fromBankName;
    private String fromBankAccountNumber;

    private String toBankName;
    private String toBankAccountNumber;

    private int moneyAmount;

    private int firmbankingStatus;

    @CommandHandler
    public FirmbankingRequestAggregate(CreateFirmbankingRequestCommand command) {
        log.info("CreateFirmbankingRequestCommand Handler");

        apply(new FirmbankingCreatedRequestEvent(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAmount()
        ));
    }

    @CommandHandler
    public String handle(UpdateFirmbankingRequestCommand command) {
        log.info("UpdateFirmbankingRequestCommand Handler");

        id = command.getAggregateIdentifier();
        apply(new UpdateFirmbankingRequestEvent(command.getFirmbankingStatus()));

        return id;
    }

    @EventSourcingHandler
    public void on(FirmbankingCreatedRequestEvent event) {
        log.info("FirmbankingCreatedRequestEvent Sourcing  Handler");

        id = UUID.randomUUID().toString();
        fromBankName = event.getFromBankName();
        fromBankAccountNumber = event.getFromBankAccountNumber();
        toBankName = event.getToBankName();
        toBankAccountNumber = event.getToBankAccountNumber();
        moneyAmount = event.getMoneyAmount();

    }

    @EventSourcingHandler
    public void on(UpdateFirmbankingRequestEvent event) {
        log.info("UpdateFirmbankingRequestEvent Sourcing  Handler");

        firmbankingStatus = event.getFirmbankingStatus();
    }

    public FirmbankingRequestAggregate() {
    }
}
