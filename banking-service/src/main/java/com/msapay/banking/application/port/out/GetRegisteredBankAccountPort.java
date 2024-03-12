package com.msapay.banking.application.port.out;

import com.msapay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.msapay.banking.application.port.in.GetRegisteredBankAccountCommand;

public interface GetRegisteredBankAccountPort {
    RegisteredBankAccountJpaEntity getRegisteredBankAccount(GetRegisteredBankAccountCommand command);
}
