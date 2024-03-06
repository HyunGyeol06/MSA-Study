package com.msapay.remittance.adapter.out.service.membership;

import com.msapay.common.CommonHttpClient;
import com.msapay.common.ExternalSystemAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msapay.remittance.apllication.port.out.membership.MembershipPort;
import com.msapay.remittance.apllication.port.out.membership.MembershipStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class MembershipServiceAdapter implements MembershipPort {

    private final CommonHttpClient membershipServiceHttpClient;

    @Value("${service.membership.url}")
    private String membershipServiceEndpoint;

    @Override
    public MembershipStatus getMembershipStatus(String membershipId) {

        String buildUrl = String.join("/", this.membershipServiceEndpoint, "membership", membershipId);
        try {
            String jsonResponse = membershipServiceHttpClient.sendGetRequest(buildUrl).body();
            ObjectMapper mapper = new ObjectMapper();

            Membership mem = mapper.readValue(jsonResponse, Membership.class);
            if (mem.isValid()){
                return new MembershipStatus(mem.getMembershipId(), true);
            } else{
                return new MembershipStatus(mem.getMembershipId(), false);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
