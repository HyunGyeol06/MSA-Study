package com.msapay.remittance.apllication.service;

import com.msapay.common.UseCase;
import com.msapay.remittance.adapter.out.persistence.RemittanceRequestMapper;
import com.msapay.remittance.apllication.port.in.FindRemittanceCommand;
import com.msapay.remittance.apllication.port.in.FindRemittanceUseCase;
import com.msapay.remittance.apllication.port.out.FindRemittancePort;
import com.msapay.remittance.domain.RemittanceRequest;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional
public class FindRemittanceService implements FindRemittanceUseCase {
    private final FindRemittancePort findRemittancePort;
    private final RemittanceRequestMapper mapper;

    @Override
    public List<RemittanceRequest> findRemittanceHistory(FindRemittanceCommand command) {
        //
        findRemittancePort.findRemittanceHistory(command);
        return null;
    }
}
