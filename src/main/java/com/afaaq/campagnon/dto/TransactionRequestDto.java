package com.afaaq.campagnon.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDto {

    @NotEmpty(message = "Campaign name must not be empty or null")
    private String campaignName;

    @Positive(message = "Amount must be positive")
    private Double amount;

    @NotEmpty(message = "Campaign name must not be empty or null")
    private String motif;
}
