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

    private MoneyChangingType moneyChangingType;

    private MoneyChangingResultStatus moneyChangingResultStatus;

    private int amount;

    public static MoneyChangingResultDetail mapToMoneyChangingResult(MoneyChangingResult moneyChangingResult) {
        return new MoneyChangingResultDetail(
          moneyChangingResult.getMoneyChangingRequestId(),
          moneyChangingResult.getMoneyChangingType(),
          moneyChangingResult.getMoneyChangingResultStatus(),
          moneyChangingResult.getAmount()
        );
    }

}

enum MoneyChangingType {
    INCREASING,
    DECREASING
}

enum MoneyChangingResultStatus {
    SUCCEEDED,
    FAILED,
    FAILED_NOT_ENOUGH_MONEY,
    FAILED_NOT_EXIST_MEMBERSHIP,
    FAILED_NOT_EXIST_MONEY_CHANGING_REQUEST
}