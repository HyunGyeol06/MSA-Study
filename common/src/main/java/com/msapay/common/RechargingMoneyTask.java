package com.msapay.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RechargingMoneyTask {

    private String taskID;

    private String taskName;

    private String membershipID;

    private List<SubTask> subTaskList;

    private String toBankName;

    private String toBankAccountNumber;

    private int moneyAmount;
}
