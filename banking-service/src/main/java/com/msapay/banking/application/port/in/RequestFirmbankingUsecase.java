package com.msapay.banking.application.port.in;

import com.msapay.banking.domain.FirmbankingRequest;

public interface RequestFirmbankingUsecase {
    FirmbankingRequest requestFirmbanking(RequestFirmbankingCommand command);

    void requestFirmbankingByEvent(RequestFirmbankingCommand command);
}
