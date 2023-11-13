package com.msastudy.membership.application.service;

import com.msastudy.common.UseCase;
import com.msastudy.membership.adapter.out.persistence.MembershipJpaEntity;
import com.msastudy.membership.adapter.out.persistence.MembershipMapper;
import com.msastudy.membership.application.port.in.FindMembershipCommand;
import com.msastudy.membership.application.port.in.FindMembershipUseCase;
import com.msastudy.membership.application.port.out.FindMembershipPort;
import com.msastudy.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
public class FindMembershipService implements FindMembershipUseCase {

    private final FindMembershipPort findMembershipPort;

    private final MembershipMapper membershipMapper;

    @Override
    public Membership findMembership(FindMembershipCommand command) {
        MembershipJpaEntity entity = findMembershipPort.findMembership(new Membership.MembershipId(command.getMembershipId()));
        return membershipMapper.mapToDomainEntity(entity);
    }
}
