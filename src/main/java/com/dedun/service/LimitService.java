package com.dedun.service;

import com.dedun.dto.request.LimitRequest;
import com.dedun.exception.MoneyErrorCode;
import com.dedun.exception.MoneyException;
import com.dedun.model.Limit;
import com.dedun.model.User;
import com.dedun.model.Wallet;
import com.dedun.repository.LimitRepository;
import com.dedun.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class LimitService {
    private final LimitRepository limitRepository;
    private final WalletRepository walletRepository;


    public LimitService(LimitRepository limitRepository,
                        WalletRepository walletRepository) {
        this.limitRepository = limitRepository;
        this.walletRepository = walletRepository;
    }

    public Limit saveLimit(int id, User user, LimitRequest limitRequest) throws MoneyException {
        Wallet wallet = walletRepository.findByUserIdAndId(user.getId(), id)
                .orElseThrow(() -> new MoneyException(MoneyErrorCode.WALLET_NOT_EXIST));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateFrom = LocalDateTime.parse(limitRequest.getDateFrom() + " 00:00:00", formatter);
        LocalDateTime dateTo = LocalDateTime.parse(limitRequest.getDateTo() + " 00:00:00", formatter);
        Limit limit = new Limit(limitRequest.getMoneyCount(), dateFrom, dateTo, wallet);
        limitRepository.save(limit);
        return limit;
    }
}
