package com.msastudy.money.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMoneyChangingRepository extends JpaRepository<MoneyChangingRequestJpaEntity, Long> {

}
