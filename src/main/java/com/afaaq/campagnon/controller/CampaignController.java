package com.afaaq.campagnon.controller;

import com.afaaq.campagnon.model.Campaign;
import com.afaaq.campagnon.service.CampaignService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/campaigns")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @GetMapping
    public ResponseEntity<List<Campaign>> getAllCampaigns() {

        List<Campaign> campaigns = campaignService.getAllCampaigns();

        return new ResponseEntity<>(campaigns, HttpStatus.OK);
    }
}
