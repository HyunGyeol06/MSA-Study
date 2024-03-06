package com.msapay.banking.application.port.out;

import com.msapay.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.msapay.banking.adapter.out.external.bank.FirmbankingResult;

public interface RequestExternalFirmbankingPort {
    FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest request);
}

