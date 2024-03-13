package com.msapay.payment.adapter.out.persistence;

import com.msapay.common.PersistenceAdapter;
import com.msapay.payment.application.port.out.CreatePaymentPort;
import com.msapay.payment.domain.Payment;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class PaymentPersistenceAdapter implements CreatePaymentPort {
    private final SpringDataPaymentRepository paymentRepository;
    private final PaymentMapper mapper;
    @Override
    public Payment createPayment(String requestMembershipId, String requestPrice, String franchiseId, String franchiseFeeRate) {
        PaymentJpaEntity jpaEntity = paymentRepository.save(
                PaymentJpaEntity.builder()
                        .requestMembershipId(requestMembershipId)
                        .requestPrice(Integer.parseInt(requestPrice))
                        .franchiseId(franchiseId)
                        .franchiseFeeRate(franchiseFeeRate)
                        .build()
        );
        return mapper.mapToDomainEntity(jpaEntity);
    }
}
