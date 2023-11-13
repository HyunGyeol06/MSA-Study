package com.msastudy.banking.application.port.out;

import com.msastudy.banking.adapter.out.persistence.FirmbankingRequestJpaEntity;
import com.msastudy.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.msastudy.banking.domain.FirmbankingRequest;
import com.msastudy.banking.domain.RegisteredBankAccount;

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
