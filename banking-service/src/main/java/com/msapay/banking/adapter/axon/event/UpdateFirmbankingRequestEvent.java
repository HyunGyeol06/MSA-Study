package com.msapay.banking.adapter.axon.event;

import lombok.*;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateFirmbankingRequestEvent {
    private int firmbankingStatus;
}
