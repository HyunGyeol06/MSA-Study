package com.msapay.banking.adapter.in.web;

import com.msapay.banking.application.port.in.RequestFirmbankingCommand;
import com.msapay.banking.application.port.in.RequestFirmbankingUsecase;
import com.msapay.banking.domain.FirmbankingRequest;
import com.msapay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestFirmbankingController {
    private final RequestFirmbankingUsecase requestFirmbankingUsecase;

    @PostMapping(path = "/banking/firmbanking/request")
    FirmbankingRequest registerMembership(@RequestBody FirmbankingRequest request) {
        RequestFirmbankingCommand command = RequestFirmbankingCommand.builder()
                .fromBankName(request.getFromBankName())
                .fromBankAccountNumber(request.getFromBankAccountNumber())
                .toBankName(request.getToBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .moneyAccount(request.getMoneyAccount())
                .build();

        return requestFirmbankingUsecase.requestFirmbanking(command);
    }
}
