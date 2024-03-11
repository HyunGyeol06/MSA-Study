package com.msapay.banking.application.port.in;

import com.msapay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateFirmbankingCommand extends SelfValidating<UpdateFirmbankingCommand> {

    @NotNull
    private final String firmbankingAggregateIdentifier;

    @NotNull
    private final int firmbaningStatus;

    public UpdateFirmbankingCommand(String firmbankingAggregateIdentifier, int firmbaningStatus) {
        this.firmbankingAggregateIdentifier = firmbankingAggregateIdentifier;
        this.firmbaningStatus = firmbaningStatus;
    }
}
