package com.afaaq.campagnon.exception;

public class CampaignAlreadyExists extends RuntimeException {
    public CampaignAlreadyExists() {
    }

    public CampaignAlreadyExists(String message) {
        super(message);
    }
}
