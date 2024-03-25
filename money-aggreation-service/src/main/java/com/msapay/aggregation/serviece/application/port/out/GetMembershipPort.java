package com.msapay.aggregation.serviece.application.port.out;


import java.util.List;

public interface GetMembershipPort {
    List<String> getMembershipByAddress(String address);
}
