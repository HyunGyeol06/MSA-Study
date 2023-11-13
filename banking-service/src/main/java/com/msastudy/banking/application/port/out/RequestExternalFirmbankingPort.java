package com.msastudy.banking.application.port.out;

import com.msastudy.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.msastudy.banking.adapter.out.external.bank.FirmbankingResult;

public interface RequestExternalFirmbankingPort {
    FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest request);
}

