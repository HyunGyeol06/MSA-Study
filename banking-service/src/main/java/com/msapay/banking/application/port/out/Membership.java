package com.msapay.banking.application.port.out;


import lombok.*;

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

