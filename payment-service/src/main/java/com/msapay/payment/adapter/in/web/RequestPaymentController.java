package com.msapay.payment.adapter.in.web;

import com.msapay.common.WebAdapter;
import com.msapay.payment.application.port.in.RequestPaymentCommand;
import com.msapay.payment.application.port.in.RequestPaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@WebAdapter
@RequiredArgsConstructor
public class RequestPaymentController {

    private final RequestPaymentUseCase requestPaymentUseCase;

    @PostMapping("/payment/request")
    void requestPayment(PaymentRequest request) {
        requestPaymentUseCase.requestPayment(
                RequestPaymentCommand.builder()
                        .requestMembershipId(request.getRequestMembershipId())
                        .requestPrice(request.getRequestPrice())
                        .franchiseId(request.getFranchiseId())
                        .franchiseFeeRate(request.getFranchiseFeeRate())
                        .build()
        );
    }

}
