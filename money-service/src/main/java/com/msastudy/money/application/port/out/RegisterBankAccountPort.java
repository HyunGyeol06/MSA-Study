package com.msastudy.money.application.port.out;

import com.msastudy.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.msastudy.banking.domain.RegisteredBankAccount;

public interface RegisterBankAccountPort {

    RegisteredBankAccountJpaEntity createRegisteredBankAccount(
            RegisteredBankAccount.MembershipId membershipId,
            RegisteredBankAccount.BankName bankName,
            RegisteredBankAccount.BankAccountNumber bankAccountNumber,
            RegisteredBankAccount.LinkedStatusValid linkedStatusValid
    );
}
