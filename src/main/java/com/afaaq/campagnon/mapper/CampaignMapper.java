package com.afaaq.campagnon.mapper;

import com.afaaq.campagnon.dto.CampaignDto;
import com.afaaq.campagnon.model.Campaign;
import org.springframework.stereotype.Service;

@Service
public class CampaignMapper {
    public CampaignDto toCampaignDto(Campaign campaign) {
        CampaignDto campaignDto = new CampaignDto();
        campaignDto.setName(campaign.getName());
        campaignDto.setDescription(campaign.getDescription());
        campaignDto.setTarget(campaign.getTarget());
        campaignDto.setImage(campaign.getImage());
        return campaignDto;
    }

    public Campaign toCampaignEntity(CampaignDto campaignDto) {
        return Campaign.builder()
                .name(campaignDto.getName())
                .description(campaignDto.getDescription())
                .target(campaignDto.getTarget())
                .image(campaignDto.getImage())
                .build();
    }
}
