package com.msastudy.membership.adapter.out.persistence;

import com.msastudy.common.PersistenceAdapter;
import com.msastudy.membership.application.port.out.FindMembershipPort;
import com.msastudy.membership.application.port.out.ModifyMembershipPort;
import com.msastudy.membership.application.port.out.RegisterMembershipPort;
import com.msastudy.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, FindMembershipPort, ModifyMembershipPort {

    private final SpringDataMembershipRepository membershipRepository;

    @Override
    public MembershipJpaEntity createMembership(Membership.MembershipName membershipName, Membership.MembershipEmail membershipEmail, Membership.MembershipAddress membershipAddress, Membership.MembershipIsValid membershipIsValid, Membership.MembershipIsCorp membershipIsCorp) {
        return membershipRepository.save(
                new MembershipJpaEntity(
                        membershipName.getNameValue(),
                        membershipEmail.getMembershipEmail(),
                        membershipAddress.getMembershipAddress(),
                        membershipIsValid.getMembershipValidValue(),
                        membershipIsCorp.getMembershipCorpValue()
                )
        );
    }

    @Override
    public MembershipJpaEntity findMembership(Membership.MembershipId membershipId) {
        return membershipRepository.getById(Long.parseLong(membershipId.getMembershipId()));
    }

    @Override
    public MembershipJpaEntity modifyMembership(Membership.MembershipId membershipId, Membership.MembershipName membershipName, Membership.MembershipEmail membershipEmail, Membership.MembershipAddress membershipAddress, Membership.MembershipIsValid membershipIsValid, Membership.MembershipIsCorp membershipIsCorp) {
        MembershipJpaEntity entity = membershipRepository.getById(Long.parseLong(membershipId.getMembershipId()));
        entity.setName(membershipName.getNameValue());
        entity.setAddress(membershipAddress.getMembershipAddress());
        entity.setEmail(membershipEmail.getMembershipEmail());
        entity.setIsValid(membershipIsValid.getMembershipValidValue());
        entity.setIsCorp(membershipIsCorp.getMembershipCorpValue());


        return membershipRepository.save(entity);
    }
}
