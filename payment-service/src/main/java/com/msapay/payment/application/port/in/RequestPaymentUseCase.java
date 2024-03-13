package com.msapay.payment.application.port.in;

public interface RequestPaymentUseCase {
    void requestPayment(RequestPaymentCommand request);
}
