package com.msapay.banking.adapter.in.web;

import com.msapay.banking.application.port.in.RequestFirmbankingCommand;
import com.msapay.banking.application.port.in.RequestFirmbankingUsecase;
import com.msapay.banking.application.port.in.UpdateFirmbankingCommand;
import com.msapay.banking.application.port.in.UpdateFirmbankingUsecase;
import com.msapay.banking.domain.FirmbankingRequest;
import com.msapay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestFirmbankingController {
    private final RequestFirmbankingUsecase requestFirmbankingUsecase;

    private final UpdateFirmbankingUsecase updateFirmbankingUsecase;

    @PostMapping(path = "/banking/firmbanking/request")
    FirmbankingRequest registerFirmbanking(@RequestBody RequestFirmbankingRequest request) {
        RequestFirmbankingCommand command = RequestFirmbankingCommand.builder()
                .fromBankName(request.getFromBankName())
                .fromBankAccountNumber(request.getFromBankAccountNumber())
                .toBankName(request.getToBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .moneyAmount(request.getMoneyAmount())
                .build();

        return requestFirmbankingUsecase.requestFirmbanking(command);
    }

    @PostMapping(path = "/banking/firmbanking/request-eda")
    void registerFirmbankingByEvent(@RequestBody RequestFirmbankingRequest request) {
        RequestFirmbankingCommand command = RequestFirmbankingCommand.builder()
                .fromBankName(request.getFromBankName())
                .fromBankAccountNumber(request.getFromBankAccountNumber())
                .toBankName(request.getToBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .moneyAmount(request.getMoneyAmount())
                .build();

         requestFirmbankingUsecase.requestFirmbankingByEvent(command);
    }

    @PutMapping(path = "/banking/firmbanking/update-eda")
    void updateMembershipByEvent(@RequestBody UpdateFirmbankingRequest request) {
        UpdateFirmbankingCommand comand = UpdateFirmbankingCommand.builder()
                .firmbankingAggregateIdentifier(request.getFirmbankingRequestAggregateIdentifier())
                .firmbaningStatus(request.getStatus())
                .build();
        updateFirmbankingUsecase.requestFirmbankingByEvent(comand);
    }
}
