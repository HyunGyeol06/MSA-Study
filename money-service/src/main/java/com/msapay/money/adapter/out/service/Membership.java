package com.msapay.money.adapter.out.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membership {

    private String membershipId;

    private String name;

    private String email;

    private String address;

    private Boolean isValid;

    private Boolean isCorp;
}

