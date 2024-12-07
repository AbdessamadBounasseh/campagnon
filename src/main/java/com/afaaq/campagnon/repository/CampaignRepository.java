package com.afaaq.campagnon.repository;

import com.afaaq.campagnon.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    Optional<Campaign> findByNameIgnoreCase(String name);

    @Query("select c from Campaign c order by c.lastUpdatedOn desc")
    List<Campaign> findAllCampaigns();

    void deleteByName(String name);
}
