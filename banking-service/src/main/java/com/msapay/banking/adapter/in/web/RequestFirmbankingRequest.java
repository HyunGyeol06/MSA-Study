package com.msapay.banking.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestFirmbankingRequest {

    private String membershipId;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;
    private Integer moneyAmount; //원단위

}
