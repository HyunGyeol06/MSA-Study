package com.msastudy.money.adapter.in.web;

import com.msastudy.money.domain.MoneyChangingRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyChangingResultDetail {
    private String moneyChangingRequestId;

    private int moneyChangingType;

    private int moneyChangingResultStatus;

    private int amount;



}