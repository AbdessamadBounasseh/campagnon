package com.afaaq.campagnon.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignResponseDto extends CampaignDto {
    Double restAmount;

    List<TransactionResponseDto> transactions;
}
