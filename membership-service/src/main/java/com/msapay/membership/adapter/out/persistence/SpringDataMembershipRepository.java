package com.msapay.membership.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataMembershipRepository extends JpaRepository<MembershipJpaEntity, Long> {
    List<MembershipJpaEntity> findByAddress(String address);

}
