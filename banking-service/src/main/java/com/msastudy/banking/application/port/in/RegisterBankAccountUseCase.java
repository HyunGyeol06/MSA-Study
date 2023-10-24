package com.msastudy.banking.application.port.in;

import com.msastudy.banking.domain.RegisteredBankAccount;


public interface RegisterBankAccountUsecase {
    RegisteredBankAccount registerMembership(RegisterBankAccountCommand command);
}
