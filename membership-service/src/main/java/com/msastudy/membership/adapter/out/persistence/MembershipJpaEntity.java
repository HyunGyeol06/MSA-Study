package com.msastudy.membership.adapter.out.persistence;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "membership")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
//@NoArgsConstructor
public class MembershipJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long membershipId;

    private String name;

    private String address;

    private String email;

    private Boolean isValid;

    private Boolean isCorp;

    public MembershipJpaEntity(String name, String address, String email, Boolean isValid, Boolean isCorp) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.isValid = isValid;
        this.isCorp = isCorp;
    }
}
