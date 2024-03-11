package com.msapay.money.application.port.in;

import com.msapay.money.adapter.axon.command.IncreaseMemberMoneyCommand;
import com.msapay.money.domain.MoneyChangingRequest;


public interface IncreaseMoneyRequestUseCase {
    MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command);

    MoneyChangingRequest increaseMoneyRequestAsync(IncreaseMoneyRequestCommand command);

    void increaseMoneyRequestByEvent(IncreaseMoneyRequestCommand command);
}
