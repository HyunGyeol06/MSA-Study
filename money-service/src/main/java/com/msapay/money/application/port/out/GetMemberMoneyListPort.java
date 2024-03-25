package com.msapay.money.application.port.out;

import com.msapay.money.adapter.out.persistence.MemberMoneyJpaEntity;

import java.util.List;

public interface GetMemberMoneyListPort {
    List<MemberMoneyJpaEntity> getMemberMoneyPort(List<String> membershipIds);
}
