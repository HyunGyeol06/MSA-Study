package com.msapay.remittance.apllication.port.out.banking;

public interface BankingPort {

    BankingInfo getMembershipBankingInfo(String bankName, String bankAccountNumber);

    boolean requestFirmbanking(String bankName, String bankAccountNumber, int amount);
}
