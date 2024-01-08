package com.msastudy.money.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMoney {
    @Getter private final String memberMoneyId;

    @Getter private final String membershipId;

    @Getter private final int balance;

    //@Getter private final int linkedAccount;

    public static MemberMoney generateMemberMoney(
        MemberMoneyId memberMoneyId,
        MembershipId membershipId,
        Balance balance
    ){
        return new MemberMoney(
                memberMoneyId.memberMoneyId,
                membershipId.membershipId,
                balance.balance
        );
    }

    @Value
    public static class MemberMoneyId {
        public MemberMoneyId(String value) {
            this.memberMoneyId = value;
        }
        String memberMoneyId;
    }

    @Value
    public static class MembershipId {
        public MembershipId(String value) {
            this.membershipId = value;
        }
        String membershipId;
    }

    @Value
    public static class Balance {
        public Balance(int value) {
            this.balance = value;
        }
        int balance;
    }

}
