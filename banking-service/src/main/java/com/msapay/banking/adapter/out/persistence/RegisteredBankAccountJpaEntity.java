package com.msapay.banking.adapter.out.persistence;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "registered_banking_account")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
//@NoArgsConstructor
public class RegisteredBankAccountJpaEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registeredBankAccountId;

    private String membershipId;

    private String bankName;

    private String bankAccountNumber;

    private Boolean linkedStatusValid;

    public RegisteredBankAccountJpaEntity(String membershipId, String bankName, String bankAccountNumber, Boolean linkedStatusValid) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.linkedStatusValid = linkedStatusValid;
    }
}
