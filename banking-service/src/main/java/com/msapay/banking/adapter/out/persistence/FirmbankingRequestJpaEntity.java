package com.msapay.banking.adapter.out.persistence;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "request_firmbanking")
@Getter
@Setter
@RequiredArgsConstructor
public class FirmbankingRequestJpaEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestFirmbankingId;

    private String fromBankName;

    private String fromBankAccountNumber;

    private String toBankName;

    private String toBankAccountNumber;

    private int moneyAccount;

    private int firmbankingStatus;

    private UUID uuid;

    private String aggregateIdentifier;

    public FirmbankingRequestJpaEntity(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber, int moneyAccount, int firmbankingStatus, UUID uuid, String aggregateIdentifier) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAccount = moneyAccount;
        this.firmbankingStatus = firmbankingStatus;
        this.uuid = uuid;
        this.aggregateIdentifier = aggregateIdentifier;
    }

    @Override
    public String toString() {
        return "FirmbankingRequestJpaEntity{" +
                "requestFirmbankingId=" + requestFirmbankingId +
                ", fromBankName='" + fromBankName + '\'' +
                ", fromBankAccountNumber='" + fromBankAccountNumber + '\'' +
                ", toBankName='" + toBankName + '\'' +
                ", toBankAccountNumber='" + toBankAccountNumber + '\'' +
                ", moneyAccount=" + moneyAccount +
                ", firmbankingStatus=" + firmbankingStatus +
                ", uuid=" + uuid +
                ", aggregateIdentifier='" + aggregateIdentifier + '\'' +
                '}';
    }
}
