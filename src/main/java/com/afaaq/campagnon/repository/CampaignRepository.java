package com.afaaq.campagnon.repository;

import com.afaaq.campagnon.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    Optional<Campaign> findByNameIgnoreCase(String name);
}
