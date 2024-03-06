package com.msapay.remittance.apllication.port.out;

import com.msapay.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import com.msapay.remittance.apllication.port.in.RequestRemittanceCommand;

public interface RequestRemittancePort {

    RemittanceRequestJpaEntity createRemittanceRequestHistory(RequestRemittanceCommand command);
    boolean saveRemittanceRequestHistory(RemittanceRequestJpaEntity entity);
}
