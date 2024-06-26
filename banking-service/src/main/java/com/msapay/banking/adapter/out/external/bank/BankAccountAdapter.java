package com.msapay.banking.adapter.out.external.bank;

import com.msapay.banking.adapter.out.persistence.SpringDataRegisteredBankAccountRepository;
import com.msapay.banking.application.port.out.RequestBankAccountInfoPort;
import com.msapay.banking.application.port.out.RequestExternalFirmbankingPort;
import com.msapay.common.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountAdapter implements RequestBankAccountInfoPort, RequestExternalFirmbankingPort {

    private final SpringDataRegisteredBankAccountRepository bankAccountRepository;


    @Override
    public BankAccount getBankAccountInfo(GetBankAccountRequest request) {

        return new BankAccount(request.getBankName(), request.getBankAccountNumber(), true);
    }

    @Override
    public FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest request) {

        return new FirmbankingResult(1);
    }
}
