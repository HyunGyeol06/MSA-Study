package com.msapay.banking.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataFirmbankingRequestRepository extends JpaRepository<FirmbankingRequestJpaEntity, Long> {
    Optional<List<FirmbankingRequestJpaEntity>> findByAggregateIdentifier(String aggregateIdentifier);

}
