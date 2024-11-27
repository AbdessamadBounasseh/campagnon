package com.afaaq.campagnon.service;

import com.afaaq.campagnon.dto.CampaignRequestDto;
import com.afaaq.campagnon.dto.CampaignResponseDto;
import com.afaaq.campagnon.exception.CampaignNotFoundException;
import com.afaaq.campagnon.mapper.CampaignMapper;
import com.afaaq.campagnon.model.Campaign;
import com.afaaq.campagnon.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignMapper campaignMapper;

    public List<CampaignResponseDto> getAllCampaigns() {
        return campaignRepository.findAll().stream().map(
                campaignMapper::toCampaignResponseDto
        ).toList();
    }

    public Campaign getCampaignByName(String name) {
        return campaignRepository.findByNameIgnoreCase(name).orElseThrow(
                () -> new CampaignNotFoundException("Campaign by name: " + name + " not found !")
        );
    }

    public CampaignResponseDto getCampaignDtoByName(String name) {
        Campaign campaign = campaignRepository.findByNameIgnoreCase(name).orElseThrow(
                () -> new CampaignNotFoundException("Campaign by name: " + name + " not found !")
        );
        return campaignMapper.toCampaignResponseDto(campaign);
    }

    public void createNewCampaign(CampaignRequestDto campaignResponseDTO) {
        Campaign campaign = campaignMapper.toCampaignEntity(campaignResponseDTO);
        campaignRepository.save(campaign);
    }

    public void addAmount(Campaign campaign, Double amount) {
        campaign.setCurrentAmount(campaign.getCurrentAmount() + amount);
    }

    public void saveCampaign(Campaign campaign) {
        campaignRepository.save(campaign);
    }
}
