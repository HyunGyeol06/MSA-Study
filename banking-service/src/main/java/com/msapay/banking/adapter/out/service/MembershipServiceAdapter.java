package com.msapay.banking.adapter.out.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msapay.banking.application.port.out.GetMembershipPort;
import com.msapay.banking.application.port.out.Membership;
import com.msapay.common.CommonHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MembershipServiceAdapter implements GetMembershipPort {

    private final CommonHttpClient commonHttpClient;

    private final String membershipServiceUrl;

    public MembershipServiceAdapter(CommonHttpClient commonHttpClient,
                                    @Value("${service.membership.url}") String membershipServiceUrl) {
        this.commonHttpClient = commonHttpClient;
        this.membershipServiceUrl = membershipServiceUrl;
    }

    @Override
    public MembershipStatus getMembership(String membershipId) {

        //http client required
        String url = String.join("/", membershipServiceUrl, "membership", membershipId);
        try {
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();

            ObjectMapper mapper = new ObjectMapper();
            Membership membership = mapper.readValue(jsonResponse, Membership.class);

            if (membership.getIsValid()) {
                return new MembershipStatus(membership.getMembershipId(), true);
            } else {
                return new MembershipStatus(membership.getMembershipId(), false);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
