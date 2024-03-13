package com.msapay.payment.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
public class RequestPaymentCommand {

    private String requestMembershipId;

    private String requestPrice;

    private String franchiseId;

    private String franchiseFeeRate;

//    private int paymentStatus;
//
//    private Date approvedDate;
}
