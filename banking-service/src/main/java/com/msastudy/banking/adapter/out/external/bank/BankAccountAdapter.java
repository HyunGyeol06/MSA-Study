package com.msastudy.banking.adapter.out.external.bank;

import com.msastudy.banking.adapter.out.persistence.SpringDataRegisteredBankAccountRepository;
import com.msastudy.banking.application.port.out.RequestBankAccountInfoPort;
import com.msastudy.banking.application.port.out.RequestExternalFirmbankingPort;
import com.msastudy.common.ExternalSystemAdapter;
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
