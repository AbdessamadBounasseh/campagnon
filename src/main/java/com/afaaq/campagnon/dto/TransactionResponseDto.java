package com.afaaq.campagnon.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {

    private String campaignName;

    private Double amount;

    private String motif;
}
