package com.msapay.money.application.port.in;

import com.msapay.money.domain.MoneyChangingRequest;


public interface IncreaseMoneyRequestUseCase {
    MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command);

    MoneyChangingRequest increaseMoneyRequestAsync(IncreaseMoneyRequestCommand command);
}
