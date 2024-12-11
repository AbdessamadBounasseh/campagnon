package com.afaaq.campagnon.service;

import com.afaaq.campagnon.dto.CampaignRequestDto;
import com.afaaq.campagnon.dto.CampaignResponseDto;
import com.afaaq.campagnon.exception.CampaignAlreadyExists;
import com.afaaq.campagnon.exception.CampaignNotFoundException;
import com.afaaq.campagnon.mapper.CampaignMapper;
import com.afaaq.campagnon.model.Campaign;
import com.afaaq.campagnon.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignMapper campaignMapper;

    public List<CampaignResponseDto> getAllCampaigns() {
        return campaignRepository.findAllCampaigns().stream().map(
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
        sleep();

        Campaign campaign = campaignMapper.toCampaignEntity(campaignResponseDTO);
        campaignRepository.save(campaign);
    }

    public void addAmount(Campaign campaign, Double amount) {
        campaign.setCurrentAmount(campaign.getCurrentAmount() + amount);
    }

    public void saveCampaign(Campaign campaign) {
        campaignRepository.save(campaign);
    }

    public void updateCampaign(String name, CampaignRequestDto campaignRequest) {
        sleep();

        // Verify if campaign by ID exists
        Campaign campaign = getCampaignByName(name);

        // Verify if name is unique
        Optional<Campaign> existingCampaign = campaignRepository.findByNameIgnoreCase(campaignRequest.getName());
        if (existingCampaign.isPresent() && !existingCampaign.get().getName().equals(campaign.getName())) {
            throw new CampaignAlreadyExists("Campaign by name '" + existingCampaign.get().getName() + "' already exists !");
        }

        // Update campaign
        Campaign campaignUpdate = campaignMapper.toCampaignEntity(campaignRequest);
        campaignUpdate.setId(campaign.getId());
        campaignUpdate.setCurrentAmount(campaign.getCurrentAmount());
        campaignRepository.save(campaignUpdate);
    }

    @Transactional
    public void deleteCampaignByName(String name) {
        campaignRepository.deleteByName(name);
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Error occurred in thread !");
        }
    }
}
