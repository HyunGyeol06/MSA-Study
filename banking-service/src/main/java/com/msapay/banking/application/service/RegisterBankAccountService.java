package com.msapay.banking.application.service;

import com.msapay.banking.adapter.axon.command.CreateRegisteredBankAccountCommand;
import com.msapay.banking.adapter.out.external.bank.BankAccount;
import com.msapay.banking.adapter.out.external.bank.GetBankAccountRequest;
import com.msapay.banking.application.port.in.GetRegisteredBankAccountCommand;
import com.msapay.banking.application.port.in.GetRegisteredBankAccountUseCase;
import com.msapay.banking.application.port.out.*;
import com.msapay.banking.domain.RegisteredBankAccount;
import com.msapay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.msapay.banking.application.port.in.RegisterBankAccountCommand;
import com.msapay.banking.application.port.in.RegisterBankAccountUseCase;

import com.msapay.common.UseCase;
import com.msapay.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@UseCase
public class RegisterBankAccountService implements RegisterBankAccountUseCase, GetRegisteredBankAccountUseCase {

    private final GetMembershipPort getMembershipPort;
    private final RegisterBankAccountPort registerBankAccountPort;
    private final RegisteredBankAccountMapper registeredBankAccountMapper;
    private final RequestBankAccountInfoPort requestBankAccountInfoPort;
    private final GetRegisteredBankAccountPort getRegisteredBankAccountPort;
    private final CommandGateway commandGateway;

    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {

        MembershipStatus membershipStatus = getMembershipPort.getMembership(command.getMembershipId());
        if(!membershipStatus.isValid()) {
            return null;
        }

        BankAccount accountInfo = requestBankAccountInfoPort.getBankAccountInfo(new GetBankAccountRequest(command.getBankName(), command.getBankAccountNumber()));
        boolean accountIsValid =  accountInfo.getIsValid();

        if(accountIsValid) {
            // 등록 정보 저장
            RegisteredBankAccountJpaEntity savedAccountInfo = registerBankAccountPort.createRegisteredBankAccount(
                    new RegisteredBankAccount.MembershipId(command.getMembershipId()+""),
                    new RegisteredBankAccount.BankName(command.getBankName()),
                    new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                    new RegisteredBankAccount.LinkedStatusIsValid(command.getIsValid()),
                    new RegisteredBankAccount.AggregateIdentifier(""));

            return registeredBankAccountMapper.mapToDomainEntity(savedAccountInfo);
        } else {
            return null;
        }

    }

    @Override
    public void registerBankAccountByEvent(RegisterBankAccountCommand command) {
        commandGateway.send(new CreateRegisteredBankAccountCommand(command.getMembershipId(), command.getBankName(), command.getBankAccountNumber()))
                .whenComplete(
                        (result, throwable) -> {
                            if(throwable != null) {
                                throwable.printStackTrace();
                            } else {
                                // 정상적으로 이벤트 소싱.
                                // -> registeredBankAccount 를 insert
                                registerBankAccountPort.createRegisteredBankAccount(
                                        new RegisteredBankAccount.MembershipId(command.getMembershipId()+""),
                                        new RegisteredBankAccount.BankName(command.getBankName()),
                                        new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                                        new RegisteredBankAccount.LinkedStatusIsValid(command.getIsValid()),
                                        new RegisteredBankAccount.AggregateIdentifier(result.toString()));
                            }
                        }
                );
    }

    @Override
    public RegisteredBankAccount getRegisteredBankAccount(GetRegisteredBankAccountCommand command) {
        return registeredBankAccountMapper.mapToDomainEntity(getRegisteredBankAccountPort.getRegisteredBankAccount(command));
    }
}
