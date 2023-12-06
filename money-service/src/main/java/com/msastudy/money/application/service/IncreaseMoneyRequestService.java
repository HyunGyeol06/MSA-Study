package com.msastudy.money.application.service;

import com.msastudy.banking.adapter.out.external.bank.BankAccount;
import com.msastudy.banking.adapter.out.external.bank.GetBankAccountRequest;
import com.msastudy.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.msastudy.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import com.msastudy.banking.application.port.in.RegisterBankAccountCommand;
import com.msastudy.banking.application.port.in.RegisterBankAccountUseCase;
import com.msastudy.banking.application.port.out.RegisterBankAccountPort;
import com.msastudy.banking.application.port.out.RequestBankAccountInfoPort;
import com.msastudy.banking.domain.RegisteredBankAccount;
import com.msastudy.common.UseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@UseCase
public class IncreaseMoneyRequestService implements RegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerBankAccountPort;
    private final RegisteredBankAccountMapper registeredBankAccountMapper;

    private final RequestBankAccountInfoPort requestBankAccountInfoPort;
    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {

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
