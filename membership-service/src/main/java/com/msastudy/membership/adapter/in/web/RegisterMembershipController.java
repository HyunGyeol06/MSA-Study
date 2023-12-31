package com.msastudy.membership.adapter.in.web;

import com.msastudy.common.WebAdapter;
import com.msastudy.membership.application.port.in.RegisterMembershipCommand;
import com.msastudy.membership.application.port.in.RegisterMembershipUseCase;
import com.msastudy.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterMembershipController {

    private final RegisterMembershipUseCase registerMembershipUseCase;

    @PostMapping(path = "/membership/register")
    Membership registerMembership(@RequestBody RegisterMembershipRequest request){
        RegisterMembershipCommand command = RegisterMembershipCommand.builder()
                .name(request.getName())
                .email(request.getEmail())
                .address(request.getAddress())
                .isValid(true)
                .isCorp(request.getIsCorp())
                .build();

        return registerMembershipUseCase.registerMembership(command);
    }
}
