package com.msapay.money.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataMemberMoneyRepository extends JpaRepository<MemberMoneyJpaEntity, Long> {

    List<MemberMoneyJpaEntity> findByMembershipId(Long membershipId);
}
