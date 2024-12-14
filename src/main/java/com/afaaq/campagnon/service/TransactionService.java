package com.afaaq.campagnon.service;

import com.afaaq.campagnon.dto.TransactionRequestDto;
import com.afaaq.campagnon.mapper.TransactionMapper;
import com.afaaq.campagnon.model.Campaign;
import com.afaaq.campagnon.model.Transaction;
import com.afaaq.campagnon.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final CampaignService campaignService;

    @Override
    public void createTransaction(TransactionRequestDto transactionRequestDto) {
        Campaign campaign = campaignService.getCampaignByName(transactionRequestDto.getCampaignName());
        Transaction transaction = transactionMapper.toTransactionDto(transactionRequestDto);

        campaignService.addAmount(campaign, transaction.getAmount());

        transactionRepository.save(transaction);
        campaignService.saveCampaign(campaign);
    }
}
