package com.afaaq.campagnon.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignDto {

    @NotEmpty(message = "Name should not be empty")
    String name;

    String description;

    @Positive(message = "Target value should be positive")
    Double target;

    String image;
}
