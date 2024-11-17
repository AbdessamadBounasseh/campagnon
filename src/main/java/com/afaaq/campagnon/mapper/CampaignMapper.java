package com.afaaq.campagnon.mapper;

import com.afaaq.campagnon.dto.CampaignDto;
import com.afaaq.campagnon.dto.CampaignResponseDto;
import com.afaaq.campagnon.model.Campaign;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class CampaignMapper {

    private final TransactionMapper transactionMapper;

    public CampaignMapper(@Lazy TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

    public CampaignResponseDto toCampaignResponseDto(Campaign campaign) {
        CampaignResponseDto campaignResponseDto = new CampaignResponseDto();
        campaignResponseDto.setName(campaign.getName());
        campaignResponseDto.setDescription(campaign.getDescription());
        campaignResponseDto.setTarget(campaign.getTarget());
        campaignResponseDto.setImage(campaign.getImage());
        campaignResponseDto.setRestAmount(campaign.getRestAmount());
        campaignResponseDto.setImage(campaign.getImage());
        campaignResponseDto.setTransactions(
                campaign.getTransactions().stream().map(
                        transactionMapper::toTransactionDto
                ).toList()
        );
        return campaignResponseDto;
    }

    public Campaign toCampaignEntity(CampaignDto campaignDto) {
        return Campaign.builder()
                .name(campaignDto.getName())
                .description(campaignDto.getDescription())
                .target(campaignDto.getTarget())
                .image(campaignDto.getImage())
                .currentAmount(campaignDto.getCurrentAmount())
                .build();
    }
}
