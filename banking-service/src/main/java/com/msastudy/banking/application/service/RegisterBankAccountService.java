package com.msastudy.banking.application.service;

import com.msastudy.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.msastudy.banking.application.port.in.RegisterBankAccountCommand;
import com.msastudy.banking.application.port.in.RegisterBankAccountUseCase;

import com.msastudy.banking.application.port.out.RegisterBankAccountPort;
import com.msastudy.banking.domain.RegisteredBankAccount;
import com.msastudy.common.UseCase;
import com.msastudy.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@UseCase
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerBankAccountPort;
    private final RegisteredBankAccountMapper registeredBankAccountMapper;
    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {
         RegisteredBankAccountJpaEntity jpaEntity = registerBankAccountPort.createMembership(
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsValid(command.getIsValid()),
                new Membership.MembershipIsCorp(command.getIsCorp())
        );
        return registeredBankAccountMapper.mapToDomainEntity(jpaEntity);
    }
}
