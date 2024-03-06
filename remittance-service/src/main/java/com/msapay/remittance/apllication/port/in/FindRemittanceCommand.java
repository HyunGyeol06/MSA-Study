package com.msapay.remittance.apllication.port.in;


import com.msapay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class FindRemittanceCommand extends SelfValidating<FindRemittanceCommand> {

    @NotNull
    private String membershipId; // from membership

    public FindRemittanceCommand(String membershipId) {
        this.membershipId = membershipId;
        this.validateSelf();
    }
}
