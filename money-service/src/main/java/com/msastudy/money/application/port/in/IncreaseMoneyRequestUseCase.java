package com.msastudy.money.application.port.in;

import com.msastudy.money.domain.MoneyChangingRequest;


public interface IncreaseMoneyRequestUseCase {
    MoneyChangingRequest registerBankAccount(IncreaseMoneyRequestCommand command);
}
