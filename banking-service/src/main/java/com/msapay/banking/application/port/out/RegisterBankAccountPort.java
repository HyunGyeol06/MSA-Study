package com.msapay.banking.application.port.out;

import com.msapay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.msapay.banking.domain.RegisteredBankAccount;

public interface RegisterBankAccountPort {

    RegisteredBankAccountJpaEntity createRegisteredBankAccount(
            RegisteredBankAccount.MembershipId membershipId,
            RegisteredBankAccount.BankName bankName,
            RegisteredBankAccount.BankAccountNumber bankAccountNumber,
            RegisteredBankAccount.LinkedStatusValid linkedStatusValid
    );
}
