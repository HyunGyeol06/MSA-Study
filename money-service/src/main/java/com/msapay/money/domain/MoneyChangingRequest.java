package com.msapay.money.domain;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MoneyChangingRequest {

    @Getter private final String moneyChangingRequestId;

    @Getter private final String targetMemberShipId;

    @Getter private final int changingType; //enum



    @Getter private final int changingMoneyAmount;

    @Getter private final int changingMoneyStatus; // enum


    @Getter private final UUID uuid;

    //@Getter private final Date createdAt;

    public static MoneyChangingRequest generateMoneyChangingRequest(
            MoneyChangingRequest.MoneyChangingRequestId moneyChangingRequestId,
            MoneyChangingRequest.TargetMemberShipId targetMemberShipId,
            MoneyChangingRequest.ChangingTypeValue changingType,
            MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
            MoneyChangingRequest.ChangingMoneyStatusValue changingMoneyStatus,
            MoneyChangingRequest.Uuid uuid

    ) {
        return new MoneyChangingRequest(
                moneyChangingRequestId.getMoneyChangingRequestId(),
                targetMemberShipId.getTargetMemberShipId(),
                changingType.getChangingType(),
                changingMoneyAmount.getChangingMoneyAmount(),
                changingMoneyStatus.getChangingMoneyStatus(),
                uuid.getUuid()
        );
    }

    @Value
    public static class MoneyChangingRequestId {
        public MoneyChangingRequestId(String value) {
            this.moneyChangingRequestId = value;
        }
        String moneyChangingRequestId;
    }


    @Value
    public static class TargetMemberShipId {
        public TargetMemberShipId(String value) {
            this.targetMemberShipId = value;
        }
        String targetMemberShipId;
    }

    @Value
    public static class ChangingTypeValue {
        public ChangingTypeValue(int value) {
            this.changingType = value;
        }
        int changingType;
    }

    @Value
    public static class ChangingMoneyAmount {
        public
        ChangingMoneyAmount(int value) {
            this.changingMoneyAmount = value;
        }
        int changingMoneyAmount;
    }

    @Value
    public static class ChangingMoneyStatusValue {
        public ChangingMoneyStatusValue(int value) {
            this.changingMoneyStatus = value;
        }
        int changingMoneyStatus;
    }

    @Value
    public static class Uuid {
        public Uuid(UUID value) {
            this.uuid = value;
        }
        UUID uuid;
    }

    @Value
    public static class CreatedAt {
        public CreatedAt(Date value) {
            this.createdAt = value;
        }
        Date createdAt;
    }


}
