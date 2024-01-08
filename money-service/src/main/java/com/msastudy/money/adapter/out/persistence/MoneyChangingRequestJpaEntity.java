package com.msastudy.money.adapter.out.persistence;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "money_changing_request")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
//@NoArgsConstructor
public class MoneyChangingRequestJpaEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moneyChangingRequestId;

    private String targetMembershipId;

    private int moneyChangingType;

    private int moneyAmount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    private int changingMoneyStatus;

    private UUID uuid;

    public MoneyChangingRequestJpaEntity(String targetMembershipId, int moneyChangingType, int moneyAmount, Timestamp timestamp, int changingMoneyStatus, UUID uuid) {
        this.targetMembershipId = targetMembershipId;
        this.moneyChangingType = moneyChangingType;
        this.moneyAmount = moneyAmount;
        this.timestamp = timestamp;
        this.changingMoneyStatus = changingMoneyStatus;
        this.uuid = uuid;
    }
}
