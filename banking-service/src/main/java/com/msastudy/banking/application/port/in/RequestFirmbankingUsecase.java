package com.msastudy.banking.application.port.in;

import com.msastudy.banking.domain.FirmbankingRequest;

public interface RequestFirmbankingUsecase {
    FirmbankingRequest requestFirmbanking(RequestFirmbankingCommand command);
}
