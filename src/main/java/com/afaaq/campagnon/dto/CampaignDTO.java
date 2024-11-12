package com.afaaq.campagnon.dto;

import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class CampaignDTO {

    String name;

    String description;

    @Positive(message = "Target value should be positive")
    Double target;

    String image;
}
