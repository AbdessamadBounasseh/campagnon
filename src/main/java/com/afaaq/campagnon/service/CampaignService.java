package com.afaaq.campagnon.service;

import com.afaaq.campagnon.dto.CampaignDTO;
import com.afaaq.campagnon.exception.CampaignNotFoundException;
import com.afaaq.campagnon.model.Campaign;
import com.afaaq.campagnon.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public Campaign getCampaignByName(String name) {
        return campaignRepository.findByName(name).orElseThrow(
                () -> new CampaignNotFoundException("Campaign by name: " + name + " not found !")
        );
    }

    public void createNewCampaign(CampaignDTO campaignDTO) {
        Campaign campaign = new Campaign();
        campaign.setName(campaignDTO.getName());
        campaign.setDescription(campaignDTO.getDescription());
        campaign.setTarget(campaignDTO.getTarget());
        campaign.setImage(campaignDTO.getImage());

        campaignRepository.save(campaign);
    }
}
