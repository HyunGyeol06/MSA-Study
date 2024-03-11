package com.msapay.banking.application.port.in;

import com.msapay.banking.domain.FirmbankingRequest;

public interface UpdateFirmbankingUsecase {
    void requestFirmbankingByEvent(UpdateFirmbankingCommand command);
}
