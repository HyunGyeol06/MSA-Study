package com.msapay.payment.application.service;

import com.msapay.common.UseCase;
import com.msapay.payment.application.port.in.RequestPaymentCommand;
import com.msapay.payment.application.port.in.RequestPaymentUseCase;

@UseCase
public class PaymentService implements RequestPaymentUseCase {
    @Override
    public void requestPayment(RequestPaymentCommand request) {

    }
}
