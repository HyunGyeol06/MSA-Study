package com.msapay.money.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncreaseMoneyChangingRequest {
    private String targetMembershipId;
    private int amount;
}
