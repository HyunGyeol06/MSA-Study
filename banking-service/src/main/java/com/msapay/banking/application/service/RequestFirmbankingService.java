package com.msapay.banking.application.service;

import com.msapay.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.msapay.banking.adapter.out.external.bank.FirmbankingResult;
import com.msapay.banking.domain.FirmbankingRequest;
import com.msapay.banking.adapter.out.persistence.FirmbankingRequestJpaEntity;
import com.msapay.banking.adapter.out.persistence.FirmbankingRequestMapper;
import com.msapay.banking.application.port.in.RequestFirmbankingCommand;
import com.msapay.banking.application.port.in.RequestFirmbankingUsecase;
import com.msapay.banking.application.port.out.RequestExternalFirmbankingPort;
import com.msapay.banking.application.port.out.RequestFirmbankingPort;
import com.msapay.common.UseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.UUID;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RequestFirmbankingService implements RequestFirmbankingUsecase {

    private final RequestFirmbankingPort requestFirmbankingPort;

    private final FirmbankingRequestMapper mapper;

    private final RequestExternalFirmbankingPort requestExternalFirmbankingPort;

    @Override
    public FirmbankingRequest requestFirmbanking(RequestFirmbankingCommand command) {

        FirmbankingRequestJpaEntity requestedEntity = requestFirmbankingPort.createFirmbankingRequest(
                new FirmbankingRequest.FromBankName(command.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(command.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAccount(command.getMoneyAccount()),
                new FirmbankingRequest.FirmbankingStatus(0)
        );

        FirmbankingResult result = requestExternalFirmbankingPort.requestExternalFirmbanking(new ExternalFirmbankingRequest(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber()
        ));

        UUID randomUUID = UUID.randomUUID();
        requestedEntity.setUuid(randomUUID);

        if (result.getResultCode() == 0) {
            //성공
            requestedEntity.setFirmbankingStatus(1);
        } else {
            //실패
            requestedEntity.setFirmbankingStatus(2);
        }



        return mapper.mapToDomainEntity(requestFirmbankingPort.modifyFirmbankingRequest(requestedEntity), randomUUID);
    }
}
