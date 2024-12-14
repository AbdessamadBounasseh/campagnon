package com.afaaq.campagnon.repository;

import com.afaaq.campagnon.model.Campaign;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CampaignRepositoryTest {

    @Autowired
    CampaignRepository campaignRepository;

    @Test
    void CampaignRepository_findByNameIgnoreCase_ReturnsCampaignSuccessfully() {
        // Given || Arrange
        String campaignName = "Masjid";
        double target = 10_000_000.;
        Campaign campaign = Campaign.builder()
                .name(campaignName)
                .target(target)
                .build();
        campaignRepository.save(campaign);

        // When || Act
        Optional<Campaign> optionalCampaign = campaignRepository.findByNameIgnoreCase(campaignName);

        // Then || Assert
        assertTrue(optionalCampaign.isPresent());
        assertEquals(campaign, optionalCampaign.get());
    }

    @Test
    void CampaignRepository_findByNameIgnoreCase_ReturnEmpty() {
        // Given
        String campaignName = "X-Name";

        // When
        Optional<Campaign> optionalCampaign = campaignRepository.findByNameIgnoreCase(campaignName);

        // Then
        assertTrue(optionalCampaign.isEmpty());
    }

    @Test
    void CampaignRepository_findByNameIgnoreCase_ReturnCampaignIgnoringCase() {
        // Arrange
        String upperCaseCampaignName = "X-NAME";

        String lowerCaseCampaignName = "x-name";
        double target = 10_000_000.;
        Campaign campaign = Campaign.builder()
                .name(lowerCaseCampaignName)
                .target(target)
                .build();
        campaignRepository.save(campaign);

        // Act
        Optional<Campaign> optionalCampaign = campaignRepository.findByNameIgnoreCase(upperCaseCampaignName);

        // Assert
        assertTrue(optionalCampaign.isPresent());
        assertEquals(campaign, optionalCampaign.get());
    }

    @Test
    void CampaignRepository_findAllCampaigns_shouldReturnAllCampaignsOrderByLastUpdatedOn() {
        // Given
        String lastUpdatedCampaignName = "Campaign N";
        Campaign lastUpdatedCampaign = Campaign.builder()
                .name(lastUpdatedCampaignName)
                .target(10_000.)
                .lastUpdatedOn(Instant.now()) //10h00
                .build();
        String earlyUpdatedCampaignName = "Campaign 1";
        Campaign earlyUpdatedCampaign = Campaign.builder()
                .name(earlyUpdatedCampaignName)
                .target(5_000.)
                .lastUpdatedOn(Instant.now().minusSeconds(60)) //09h59
                .build();

        campaignRepository.save(lastUpdatedCampaign);
        campaignRepository.save(earlyUpdatedCampaign);

        // When
        List<Campaign> campaignList = campaignRepository.findAllCampaigns();

        // Then
        assertNotNull(campaignList);
        assertEquals(2, campaignList.size());
        assertEquals(lastUpdatedCampaignName, campaignList.get(1).getName());
        assertEquals(earlyUpdatedCampaignName, campaignList.get(0).getName());
    }
}