package com.msapay.remittance.apllication.port.out;

import com.msapay.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import com.msapay.remittance.apllication.port.in.FindRemittanceCommand;

import java.util.List;

public interface FindRemittancePort {

    List<RemittanceRequestJpaEntity> findRemittanceHistory(FindRemittanceCommand command);
}
