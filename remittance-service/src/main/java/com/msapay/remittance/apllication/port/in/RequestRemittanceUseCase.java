package com.msapay.remittance.apllication.port.in;

import com.msapay.remittance.domain.RemittanceRequest;

public interface RequestRemittanceUseCase {
    RemittanceRequest requestRemittance(RequestRemittanceCommand command);
}