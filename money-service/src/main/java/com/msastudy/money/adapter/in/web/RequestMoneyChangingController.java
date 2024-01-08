package com.msastudy.money.adapter.in.web;


import com.msastudy.common.WebAdapter;
import com.msastudy.money.application.port.in.IncreaseMoneyRequestCommand;
import com.msastudy.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.msastudy.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestMoneyChangingController {

    private final IncreaseMoneyRequestUseCase increaseMoneyRequestUseCase;

    @PostMapping(path = "/money/increase")
    MoneyChangingResultDetail increaseMoneyChangingRequest(@RequestBody IncreaseMoneyChangingRequest request){
        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand.builder()
                .targetMembershipId(request.getTargetMembershipId())
                .amount(request.getAmount())
                .build();

        MoneyChangingRequest moneyChangingRequest = increaseMoneyRequestUseCase.registerBankAccount(command);

        MoneyChangingResultDetail resultDetail = new MoneyChangingResultDetail(
                moneyChangingRequest.getMoneyChangingRequestId(),
                0,
                0,
                moneyChangingRequest.getChangingMoneyAmount()
        );

        return resultDetail;
    }

//    @PostMapping(path = "/money/decrease")
//    MoneyChangingResultDetail decreaseMoney(@RequestBody DecreaseMoneyChangingRequest request){
////
////        return increaseMoneyRequestUseCase.;
//
//        return null;
//    }
}
