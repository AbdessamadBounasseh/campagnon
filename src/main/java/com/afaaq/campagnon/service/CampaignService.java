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
public class CampaignService implements ICampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignMapper campaignMapper;

    @Override
    public List<CampaignResponseDto> getAllCampaigns() {
        return campaignRepository.findAllCampaigns().stream().map(
                campaignMapper::toCampaignResponseDto
        ).toList();
    }

    @Override
    public Campaign getCampaignByName(String name) {
        return campaignRepository.findByNameIgnoreCase(name).orElseThrow(
                () -> new CampaignNotFoundException("Campaign by name: " + name + " not found !")
        );
    }

    @Override
    public CampaignResponseDto getCampaignDtoByName(String name) {
        Campaign campaign = campaignRepository.findByNameIgnoreCase(name).orElseThrow(
                () -> new CampaignNotFoundException("Campaign by name: " + name + " not found !")
        );
        return campaignMapper.toCampaignResponseDto(campaign);
    }

    @Override
    public void createNewCampaign(CampaignRequestDto campaignResponseDTO) {
        Campaign campaign = campaignMapper.toCampaignEntity(campaignResponseDTO);
        campaignRepository.save(campaign);
    }

    @Override
    public void addAmount(Campaign campaign, Double amount) {
        campaign.setCurrentAmount(campaign.getCurrentAmount() + amount);
    }

    public void saveCampaign(Campaign campaign) {
        campaignRepository.save(campaign);
    }

    @Override
    public void updateCampaign(String name, CampaignRequestDto campaignRequest) {
        // Verify if campaign by name exists
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

    @Override
    @Transactional
    public void deleteCampaignByName(String name) {
        campaignRepository.deleteByName(name);
    }
}
