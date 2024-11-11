package com.afaaq.campagnon.repository;

import com.afaaq.campagnon.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}
