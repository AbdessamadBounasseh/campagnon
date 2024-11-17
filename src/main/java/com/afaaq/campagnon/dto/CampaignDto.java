package com.afaaq.campagnon.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
public class CampaignDto {
    @NotEmpty(message = "Name must not be empty")
    String name;

    String description;

    @Positive(message = "Target value must be positive")
    Double target;

    String image;

    Double currentAmount;
}
