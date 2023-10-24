package com.msastudy.banking.adapter.in.web;

import com.msastudy.banking.application.port.in.RegisterMembershipCommand;
import com.msastudy.banking.application.port.in.RegisterMembershipUseCase;
import com.msastudy.common.WebAdapter;
import com.msastudy.banking.domain.Membership;
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
