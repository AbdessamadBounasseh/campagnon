package com.afaaq.campagnon.service;

import com.afaaq.campagnon.dto.CampaignRequestDto;
import com.afaaq.campagnon.dto.CampaignResponseDto;
import com.afaaq.campagnon.model.Campaign;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICampaignService {
    List<CampaignResponseDto> getAllCampaigns();

    Campaign getCampaignByName(String name);

    CampaignResponseDto getCampaignDtoByName(String name);

    void createNewCampaign(CampaignRequestDto campaignResponseDTO);

    void addAmount(Campaign campaign, Double amount);

    void updateCampaign(String name, CampaignRequestDto campaignRequest);

    @Transactional
    void deleteCampaignByName(String name);
}
