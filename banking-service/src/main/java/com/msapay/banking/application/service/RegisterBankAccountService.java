package com.msapay.banking.application.service;

import com.msapay.banking.adapter.out.external.bank.BankAccount;
import com.msapay.banking.adapter.out.external.bank.GetBankAccountRequest;
import com.msapay.banking.adapter.out.service.MembershipStatus;
import com.msapay.banking.application.port.out.GetMembershipPort;
import com.msapay.banking.domain.RegisteredBankAccount;
import com.msapay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.msapay.banking.application.port.in.RegisterBankAccountCommand;
import com.msapay.banking.application.port.in.RegisterBankAccountUseCase;

import com.msapay.banking.application.port.out.RegisterBankAccountPort;
import com.msapay.banking.application.port.out.RequestBankAccountInfoPort;
import com.msapay.common.UseCase;
import com.msapay.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@UseCase
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final GetMembershipPort getMembershipPort;
    private final RegisterBankAccountPort registerBankAccountPort;
    private final RegisteredBankAccountMapper registeredBankAccountMapper;

    private final RequestBankAccountInfoPort requestBankAccountInfoPort;
    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {

        MembershipStatus membershipStatus = getMembershipPort.getMembership(command.getMembershipId());
        if (!membershipStatus.isValid()) {
            return null;
        }

        BankAccount accountInfo = requestBankAccountInfoPort.getBankAccountInfo(new GetBankAccountRequest(command.getBankName(), command.getBankAccountNumber()));
        Boolean accountIsValid = accountInfo.getIsValid();

        if (accountIsValid) {
            RegisteredBankAccountJpaEntity jpaEntity = registerBankAccountPort.createRegisteredBankAccount(
                    new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                    new RegisteredBankAccount.BankName(command.getBankName()),
                    new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                    new RegisteredBankAccount.LinkedStatusValid(command.getIsValid())
            );
            return registeredBankAccountMapper.mapToDomainEntity(jpaEntity);
        } else {
            return null;
        }

    }
}
