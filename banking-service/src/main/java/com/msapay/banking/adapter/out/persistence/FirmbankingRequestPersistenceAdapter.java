package com.msapay.banking.adapter.out.persistence;

import com.msapay.banking.domain.FirmbankingRequest;
import com.msapay.banking.application.port.out.RequestFirmbankingPort;
import com.msapay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class FirmbankingRequestPersistenceAdapter implements RequestFirmbankingPort {

    private final SpringDataFirmbankingRequestRepository firmbankingRequestRepository;

    @Override
    public FirmbankingRequestJpaEntity createFirmbankingRequest(
            FirmbankingRequest.FromBankName fromBankName,
            FirmbankingRequest.FromBankAccountNumber fromBankAccountNumber,
            FirmbankingRequest.ToBankName toBankName,
            FirmbankingRequest.ToBankAccountNumber toBankAccountNumber,
            FirmbankingRequest.MoneyAccount moneyAccount,
            FirmbankingRequest.FirmbankingStatus firmbankingStatus,
            FirmbankingRequest.FirmbankingAggregateIdentifier aggregateIdenetifier) {
        return firmbankingRequestRepository.save(new FirmbankingRequestJpaEntity(
                    fromBankName.getFromBankName(),
                    fromBankAccountNumber.getFromBankAccountNumber(),
                    toBankName.getToBankName(),
                    toBankAccountNumber.getToBankAccountNumber(),
                    moneyAccount.getMoneyAccount(),
                    firmbankingStatus.getFirmbankingStatus(),
                    UUID.randomUUID(),
                    aggregateIdenetifier.getAggregateIdentifier()
        ));
    }

    @Override
    public FirmbankingRequestJpaEntity modifyFirmbankingRequest(FirmbankingRequestJpaEntity entity) {
        return firmbankingRequestRepository.save(entity);
    }

    @Override
    public FirmbankingRequestJpaEntity getFirmbankingRequest(FirmbankingRequest.FirmbankingAggregateIdentifier firmbankingAggregateIdentifier) {
        return firmbankingRequestRepository.findByAggregateIdentifier(firmbankingAggregateIdentifier.getAggregateIdentifier())
                .orElseThrow().get(0);
    }
}
