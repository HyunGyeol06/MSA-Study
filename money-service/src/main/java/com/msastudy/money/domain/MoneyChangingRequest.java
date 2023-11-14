package com.msastudy.money.domain;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MoneyChangingRequest {

    @Getter private final String moneyChangingRequestId;

    @Getter private final String targetMemberShipId;

    @Getter private final ChangingType changingType; //enum

    private enum ChangingType {
        INCREASING,
        DECREASING
    }


    @Getter private final int changingMoneyAmount;

    @Getter private final ChangingMoneyStatus changingMoneyStatus; // enum

    private enum ChangingMoneyStatus {
        REQUESTED,
        SUCCEEDED,
        FAILED,
        CANCELED
    }


    @Getter private final UUID uuid;

    @Getter private final Date createdAt;

    public static MoneyChangingRequest generateMoneyChangingRequest(
            MoneyChangingRequest.MoneyChangingRequestId moneyChangingRequestId,
            MoneyChangingRequest.TargetMemberShipId targetMemberShipId,
            MoneyChangingRequest.ChangingTypeValue changingType,
            MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
            MoneyChangingRequest.ChangingMoneyStatusValue changingMoneyStatus,
            MoneyChangingRequest.Uuid uuid,
            MoneyChangingRequest.CreatedAt createdAt

    ) {
        return new MoneyChangingRequest(
                moneyChangingRequestId.getMoneyChangingRequestId(),
                targetMemberShipId.getTargetMemberShipId(),
                changingType.getChangingType(),
                changingMoneyAmount.getChangingMoneyAmount(),
                changingMoneyStatus.getChangingMoneyStatus(),
                uuid.getUuid(),
                createdAt.getCreatedAt()
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
        public ChangingTypeValue(ChangingType value) {
            this.changingType = value;
        }
        ChangingType changingType;
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
        public ChangingMoneyStatusValue(ChangingMoneyStatus value) {
            this.changingMoneyStatus = value;
        }
        ChangingMoneyStatus changingMoneyStatus;
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
