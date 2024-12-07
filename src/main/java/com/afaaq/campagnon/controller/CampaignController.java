package com.afaaq.campagnon.controller;

import com.afaaq.campagnon.dto.CampaignRequestDto;
import com.afaaq.campagnon.dto.CampaignResponseDto;
import com.afaaq.campagnon.service.CampaignService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/campaigns")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @GetMapping
    public ResponseEntity<List<CampaignResponseDto>> getAllCampaigns() {
        List<CampaignResponseDto> campaigns = campaignService.getAllCampaigns();
        return new ResponseEntity<>(campaigns, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CampaignResponseDto> getCampaignByName(@PathVariable String name) {
        CampaignResponseDto campaign = campaignService.getCampaignDtoByName(name);
        return new ResponseEntity<>(campaign, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createNewCampaign(@Valid @RequestBody CampaignRequestDto campaign) {
        campaignService.createNewCampaign(campaign);
        return new ResponseEntity<>(
                Map.of("message", "Campaign by name " + campaign.getName() + " successfully created !"),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{name}")
    public ResponseEntity<Map<String, String>> updateCampaign(
            @PathVariable("name") String campaignName,
            @Valid @RequestBody CampaignRequestDto campaignRequestDto) {
        campaignService.updateCampaign(campaignName, campaignRequestDto);
        return ResponseEntity.ok().body(Map.of("message", "Campaign updated successfully !"));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Map<String, String>> deleteCampaignByName(@PathVariable("name") String campaignName) {
        campaignService.deleteCampaignByName(campaignName);
        return ResponseEntity.ok().body(Map.of("message", "Campaign deleted successfully !"));
    }
}
