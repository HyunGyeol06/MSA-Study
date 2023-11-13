package com.msastudy.membership.application.service;

import com.msastudy.common.UseCase;
import com.msastudy.membership.adapter.out.persistence.MembershipJpaEntity;
import com.msastudy.membership.adapter.out.persistence.MembershipMapper;
import com.msastudy.membership.application.port.in.ModifyMembershipCommand;
import com.msastudy.membership.application.port.in.ModifyMembershipUseCase;
import com.msastudy.membership.application.port.out.ModifyMembershipPort;
import com.msastudy.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ModifyMembershipService implements ModifyMembershipUseCase {

    private final ModifyMembershipPort modifyMembershipPort;

    private final MembershipMapper membershipMapper;

    @Override
    public Membership modifyMembership(ModifyMembershipCommand command) {
        MembershipJpaEntity jpaEntity = modifyMembershipPort.modifyMembership(
                new Membership.MembershipId(command.getMembershipId()),
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsValid(command.getIsValid()),
                new Membership.MembershipIsCorp(command.getIsCorp())
        );
        return membershipMapper.mapToDomainEntity(jpaEntity);
    }
}
