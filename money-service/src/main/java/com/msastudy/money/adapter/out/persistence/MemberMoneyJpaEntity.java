package com.msastudy.money.adapter.out.persistence;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "member_Money")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
//@NoArgsConstructor
public class MemberMoneyJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberMoneyId;

    private Long membershipId;

    private int balance;


    public MemberMoneyJpaEntity(Long membershipId, int balance) {
        this.membershipId = membershipId;
        this.balance = balance;
    }
}
