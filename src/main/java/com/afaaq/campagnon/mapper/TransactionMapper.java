package com.afaaq.campagnon.mapper;

import com.afaaq.campagnon.dto.TransactionRequestDto;
import com.afaaq.campagnon.dto.TransactionResponseDto;
import com.afaaq.campagnon.model.Campaign;
import com.afaaq.campagnon.model.Transaction;
import com.afaaq.campagnon.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionMapper {

    private final CampaignService campaignService;

    public Transaction toTransactionDto(TransactionRequestDto requestDto) {
        Campaign campaignByName = campaignService.getCampaignByName(requestDto.getCampaignName());
        return Transaction.builder()
                .campaign(campaignByName)
                .amount(requestDto.getAmount())
                .motif(requestDto.getMotif())
                .build();
    }

    public TransactionResponseDto toTransactionDto(Transaction request) {
        String campaignByName = campaignService.getCampaignByName(request.getCampaign().getName()).getName();
        return TransactionResponseDto.builder()
                .campaignName(campaignByName)
                .amount(request.getAmount())
                .motif(request.getMotif())
                .build();
    }
}
