package com.msapay.money.application.port.out;

import com.msapay.common.RechargingMoneyTask;

public interface SendRechargingMoneyTaskPort {
    void sendRechargingMoneyTaskPort(
            RechargingMoneyTask task
    );
}
