package com.msastudy.banking.adapter.in.web;

import com.msastudy.banking.application.port.in.RegisterBankAccountCommand;
import com.msastudy.banking.application.port.in.RegisterBankAccountUseCase;
import com.msastudy.banking.domain.RegisteredBankAccount;
import com.msastudy.common.WebAdapter;
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
    RegisteredBankAccount registerMembership(@RequestBody RegisterBankAccountRequest request){
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
}
