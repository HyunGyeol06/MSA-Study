package com.msastudy.membership.adapter.in.web;

import com.msastudy.membership.application.port.in.RegisterMembershipCommand;
import com.msastudy.membership.application.port.in.RegisterMembershipUseCase;
import com.msastudy.membership.common.WebAdapter;
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
    void registerMembership(@RequestBody RegisterMembershipRequest request){
        RegisterMembershipCommand command = RegisterMembershipCommand.builder()
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .isValid(true)
                .isCorp(request.getIsCorp())
                .build();

        registerMembershipUseCase.registerMembership(command);
    }
}