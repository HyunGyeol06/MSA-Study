package com.msapay.banking.adapter.in.web;

import com.msapay.banking.application.port.in.RegisterBankAccountCommand;
import com.msapay.banking.application.port.in.RegisterBankAccountUseCase;
import com.msapay.banking.domain.RegisteredBankAccount;
import com.msapay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterBankAccountController {

    private final RegisterBankAccountUseCase registerBankAccountUseCase;

    @PostMapping(path = "/membership/register")
    RegisteredBankAccount registeredBankAccount(@RequestBody RegisterBankAccountRequest request){
        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(request.getMembershipId())
                .bankName(request.getBankName())
                .bankAccountNumber(request.getBankAccountNumber())
                .isValid(request.getIsValid())
                .build();

        RegisteredBankAccount registeredBankAccount =
                registerBankAccountUseCase.registerBankAccount(command);
        if (registeredBankAccount == null) {
            //TODo: Error Handling
            return null;
        }

        return registeredBankAccount;
    }

    @PostMapping(path = "/banking/account/register-eda")
    void registeredBankAccountByEvent(@RequestBody RegisterBankAccountRequest request) {
        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(request.getMembershipId())
                .bankName(request.getBankName())
                .bankAccountNumber(request.getBankAccountNumber())
                .isValid(request.getIsValid())
                .build();

        registerBankAccountUseCase.registerBankAccountByEvent(command);
    }
}
