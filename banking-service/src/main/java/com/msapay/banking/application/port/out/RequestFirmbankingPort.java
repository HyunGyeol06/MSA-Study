package com.msapay.banking.application.port.out;

import com.msapay.banking.domain.FirmbankingRequest;
import com.msapay.banking.adapter.out.persistence.FirmbankingRequestJpaEntity;

public interface RequestFirmbankingPort {

    FirmbankingRequestJpaEntity createFirmbankingRequest(
            FirmbankingRequest.FromBankName fromBankName,
            FirmbankingRequest.FromBankAccountNumber fromBankAccountNumber,
            FirmbankingRequest.ToBankName toBankName,
            FirmbankingRequest.ToBankAccountNumber toBankAccountNumber,
            FirmbankingRequest.MoneyAccount moneyAccount,
            FirmbankingRequest.FirmbankingStatus firmbankingStatus
    );

    FirmbankingRequestJpaEntity modifyFirmbankingRequest(
            FirmbankingRequestJpaEntity entity
    );
}
