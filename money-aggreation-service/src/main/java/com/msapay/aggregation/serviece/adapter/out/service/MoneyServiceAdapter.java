package com.msapay.aggregation.serviece.adapter.out.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msapay.aggregation.serviece.application.port.out.GetMoneySumPort;
import com.msapay.aggregation.serviece.application.port.out.MemberMoney;
import com.msapay.common.CommonHttpClient;
import com.msapay.common.ExternalSystemAdapter;
import org.aspectj.apache.bcel.classfile.annotation.NameValuePair;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ExternalSystemAdapter
public class MoneyServiceAdapter implements GetMoneySumPort {

    private final CommonHttpClient moneyServiceHttpClient;

    @Value("${service.money.url}")
    private String moneyServiceEndpoint;

    public MoneyServiceAdapter(CommonHttpClient commonHttpClient,
                               @Value("${service.money.url}") String moneyServiceEndpoint) {
        this.moneyServiceHttpClient = commonHttpClient;
        this.moneyServiceEndpoint = moneyServiceEndpoint;
    }
    @Override
    public List<MemberMoney> getMoneySumByMembershipIds(List<String> membershipIds) {
        String url = String.join("/", moneyServiceEndpoint, "money/member-money");
        ObjectMapper mapper = new ObjectMapper();

        try {
            FindMemberMoneyRequest request = new FindMemberMoneyRequest(membershipIds);
            String jsonResponse = moneyServiceHttpClient.sendPostRequest(url, mapper.writeValueAsString(request)).body();
            List<MemberMoney> memberMoneyList = mapper.readValue(jsonResponse, new TypeReference<>() {});
            return memberMoneyList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
