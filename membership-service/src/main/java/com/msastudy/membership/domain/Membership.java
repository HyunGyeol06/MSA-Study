package com.msastudy.membership.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Membership {

    @Getter private final String membershipId;

    @Getter private final String name;

    @Getter private final String email;

    @Getter private final String address;

    @Getter  private final Boolean isValid;

    @Getter private final Boolean isCorp;


    public static Membership generateMember(
            MembershipId membershipId,
            MembershipName membershipName,
            MembershipEmail membershipEmail,
            MembershipAddress membershipAddress,
            MembershipIsValid membershipIsValid,
            MembershipIsCorp membershipIsCorp
    ) {
        return new Membership(
                membershipId.membershipId,
                membershipName.nameValue,
                membershipEmail.membershipEmail,
                membershipAddress.membershipAddress,
                membershipIsValid.membershipValidValue,
                membershipIsCorp.membershipCorpValue
        );
    }

    @Value
    public static class MembershipId {
        public MembershipId(String value) {
            this.membershipId = value;
        }
        String membershipId;
    }

    @Value
    public static class MembershipName {
        public MembershipName(String value) {
            this.nameValue = value;
        }
        String nameValue;
    }

    @Value
    public static class MembershipEmail {
        public MembershipEmail(String value) {
            this.membershipEmail = value;
        }
        String membershipEmail;
    }

    @Value
    public static class MembershipAddress {
        public MembershipAddress(String value) {
            this.membershipAddress = value;
        }
        String membershipAddress;
    }

    @Value
    public static class MembershipIsValid {
        public MembershipIsValid(Boolean value) {
            this.membershipValidValue = value;
        }
        Boolean membershipValidValue;
    }

    @Value
    public static class MembershipIsCorp {
        public MembershipIsCorp(Boolean value) {
            this.membershipCorpValue = value;
        }
        Boolean membershipCorpValue;
    }

}
