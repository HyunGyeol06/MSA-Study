package com.msastudy.membership.adapter.in.web;

import com.msastudy.common.WebAdapter;
import com.msastudy.membership.application.port.in.ModifyMembershipCommand;
import com.msastudy.membership.application.port.in.ModifyMembershipUseCase;
import com.msastudy.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class ModifyMembershipController {
    private final ModifyMembershipUseCase modifyMembershipUsecase;

    @PostMapping(path = "/membership/modify/{membershipId}")
    ResponseEntity<Membership> modifyMembership(@RequestBody ModifyMembershipRequest request) {
        ModifyMembershipCommand command = ModifyMembershipCommand.builder()
                .membershipId(request.getMembershipId())
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .isValid(request.getIsValid())
                .isCorp(request.getIsCorp())
                .build();

        return ResponseEntity.ok(modifyMembershipUsecase.modifyMembership(command));
    }
}
