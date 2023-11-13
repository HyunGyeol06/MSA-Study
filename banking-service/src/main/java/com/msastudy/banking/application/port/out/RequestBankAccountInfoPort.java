package com.msastudy.banking.application.port.out;

import com.msastudy.banking.adapter.out.external.bank.BankAccount;
import com.msastudy.banking.adapter.out.external.bank.GetBankAccountRequest;

public interface RequestBankAccountInfoPort {
    BankAccount getBankAccountInfo(GetBankAccountRequest request);
}
