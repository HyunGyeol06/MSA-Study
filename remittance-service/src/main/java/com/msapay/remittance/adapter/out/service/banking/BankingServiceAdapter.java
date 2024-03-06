package com.msapay.remittance.adapter.out.service.banking;

import com.msapay.common.CommonHttpClient;
import com.msapay.common.ExternalSystemAdapter;
import com.msapay.remittance.apllication.port.out.banking.BankingInfo;
import com.msapay.remittance.apllication.port.out.banking.BankingPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankingServiceAdapter implements BankingPort {

    private final CommonHttpClient bankingServiceHttpClient;

    @Value("${service.banking.url}")
    private String bankingServiceEndpoint;


    @Override
    public BankingInfo getMembershipBankingInfo(String bankName, String bankAccountNumber) {

        return null;
    }

    @Override
    public boolean requestFirmbanking(String bankName, String bankAccountNumber, int amount) {
        return false;
    }
}
