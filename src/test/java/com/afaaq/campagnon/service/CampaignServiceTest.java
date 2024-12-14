package com.afaaq.campagnon.service;

import com.afaaq.campagnon.exception.CampaignNotFoundException;
import com.afaaq.campagnon.mapper.CampaignMapper;
import com.afaaq.campagnon.model.Campaign;
import com.afaaq.campagnon.repository.CampaignRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CampaignServiceTest {

    @Mock
    CampaignRepository campaignRepository;

    @Mock
    CampaignMapper campaignMapper;

    @InjectMocks
    CampaignService campaignService;

    @Test
    void CampaignService_getCampaignByName_shouldReturnCampaignByGivenName() {
        // Given
        String campaignName = "Campaign name";
        Campaign campaign1 = Campaign.builder()
                .name(campaignName)
                .target(10_000.)
                .build();

        when(campaignRepository.findByNameIgnoreCase(campaignName))
                .thenReturn(Optional.of(campaign1));

        // When
        Campaign campaign = campaignService.getCampaignByName(campaignName);

        // Then
        assertNotNull(campaign);
        verify(campaignRepository).findByNameIgnoreCase(campaignName);
        assertEquals(campaign1, campaign);
    }

    @Test
    void CampaignService_getCampaignByName_shouldThrowCampaignNotFoundException() {
        // Given
        String campaignName = "Campaign name";

        when(campaignRepository.findByNameIgnoreCase(campaignName))
                .thenReturn(Optional.empty());

        // When & Then
        Throwable campaignNotFoundException = assertThrows(
                CampaignNotFoundException.class,
                () -> campaignService.getCampaignByName(campaignName)
        );
        assertEquals("Campaign by name: " + campaignName + " not found !",
                campaignNotFoundException.getMessage());
    }

}