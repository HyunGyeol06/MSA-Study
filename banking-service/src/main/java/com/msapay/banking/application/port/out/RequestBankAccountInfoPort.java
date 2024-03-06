package com.msapay.banking.application.port.out;

import com.msapay.banking.adapter.out.external.bank.BankAccount;
import com.msapay.banking.adapter.out.external.bank.GetBankAccountRequest;

public interface RequestBankAccountInfoPort {
    BankAccount getBankAccountInfo(GetBankAccountRequest request);
}
