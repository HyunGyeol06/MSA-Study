package com.msastudy.membership.adapter.out.persistence;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "membership")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class MembershipJpaEntity {

    @Id
    @Generated
    private Long membershipId;

    private String name;

    private String address;

    private String email;

    private Boolean isValid;

    private Boolean isCorp;

}
