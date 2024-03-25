package com.msapay.aggregation.serviece.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMoneySumByAddressRequest {
    String address;
}
