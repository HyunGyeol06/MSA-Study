package com.msapay.banking.application.port.in;

import com.msapay.banking.domain.RegisteredBankAccount;


public interface RegisterBankAccountUseCase {
    RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command);
}
