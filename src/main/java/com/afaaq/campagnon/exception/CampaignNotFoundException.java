package com.afaaq.campagnon.exception;

public class CampaignNotFoundException extends RuntimeException {

    public CampaignNotFoundException() {
        super("Campaign by name not found !");
    }

    public CampaignNotFoundException(String message) {
        super(message);
    }
}
