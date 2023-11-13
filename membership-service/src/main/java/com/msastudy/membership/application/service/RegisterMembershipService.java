package com.msastudy.membership.application.service;

import com.msastudy.common.UseCase;
import com.msastudy.membership.adapter.out.persistence.MembershipJpaEntity;
import com.msastudy.membership.adapter.out.persistence.MembershipMapper;
import com.msastudy.membership.application.port.in.RegisterMembershipCommand;
import com.msastudy.membership.application.port.in.RegisterMembershipUseCase;
import com.msastudy.membership.application.port.out.RegisterMembershipPort;
import com.msastudy.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@UseCase
public class RegisterMembershipService implements RegisterMembershipUseCase {

    private final RegisterMembershipPort registerMembershipPort;
    private final MembershipMapper membershipMapper;
    @Override
    public Membership registerMembership(RegisterMembershipCommand command) {
         MembershipJpaEntity jpaEntity = registerMembershipPort.createMembership(
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsValid(command.getIsValid()),
                new Membership.MembershipIsCorp(command.getIsCorp())
        );
        return membershipMapper.mapToDomainEntity(jpaEntity);
    }
}
