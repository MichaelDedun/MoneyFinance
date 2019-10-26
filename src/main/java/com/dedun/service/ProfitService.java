package com.dedun.service;

import com.dedun.dto.request.ProfitRequest;
import com.dedun.exception.MoneyErrorCode;
import com.dedun.exception.MoneyException;
import com.dedun.model.Profit;
import com.dedun.model.User;
import com.dedun.model.Wallet;
import com.dedun.model.enums.State;
import com.dedun.repository.ProfitRepository;
import com.dedun.repository.WalletRepository;
import com.dedun.validator.ProfitValidator;
import com.dedun.validator.WalletValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfitService {
    private final ProfitRepository profitRepository;
    private final WalletRepository walletRepository;
    private final WalletValidator walletValidator;
    private final ProfitValidator profitValidator;
    private final PaymentService paymentService;

    public ProfitService(ProfitRepository profitRepository,
                         WalletRepository walletRepository,
                         WalletValidator walletValidator,
                         ProfitValidator profitValidator,
                         PaymentService paymentService) {
        this.profitRepository = profitRepository;
        this.walletRepository = walletRepository;
        this.walletValidator = walletValidator;
        this.profitValidator = profitValidator;
        this.paymentService = paymentService;
    }

    public Profit save(ProfitRequest request, User user) throws MoneyException {
        Wallet wallet = walletRepository.findByUserIdAndId(user.getId(), request.getWalletId())
                .orElseThrow(() -> new MoneyException(MoneyErrorCode.WALLET_NOT_EXIST));
        wallet.setBalance(wallet.getBalance().add(request.getMoneyCount()));
        walletRepository.save(wallet);
        Profit profit = new Profit(request.getMoneyCount(), LocalDateTime.now(), wallet);
        profit.setState(State.ACTIVE);
        profitRepository.save(profit);
        paymentService.save(profit);
        return profit;
    }

    public List<Profit> getAll(int id) {
        return profitRepository.findByWalletId(id)
                .stream()
                .filter(profit -> profit.getState().equals(State.ACTIVE))
                .collect(Collectors.toList());
    }

    public Profit get(User user, int id) throws MoneyException {
        return profitRepository.findByWalletUserIdAndId(user.getId(), id)
                .orElseThrow(() -> new MoneyException(MoneyErrorCode.PROFIT_NOT_EXIST));
    }

    public Profit edit(ProfitRequest profitRequest, User user, int id) throws MoneyException {
        Profit profit = profitRepository.findByWalletUserIdAndId(user.getId(), id)
                .orElseThrow(() -> new MoneyException(MoneyErrorCode.PROFIT_NOT_EXIST));
        Wallet wallet = profit.getWallet();
        walletValidator.checkWalletBalanceBiggerThenProfitBalance(wallet, profit);
        wallet.setBalance(wallet.getBalance().subtract(profit.getMoneyCount()));
        profit.setMoneyCount(profitRequest.getMoneyCount());
        wallet.setBalance(wallet.getBalance().add(profit.getMoneyCount()));
        walletRepository.save(wallet);
        profitRepository.save(profit);
        paymentService.edit(profit);
        return profit;
    }

    public Profit updateState(User user, int id) throws MoneyException {
        Profit profit = profitRepository.findByWalletUserIdAndId(user.getId(), id)
                .orElseThrow(() -> new MoneyException(MoneyErrorCode.PROFIT_NOT_EXIST));
        profitValidator.checkProfitIsActive(profit);
        profit.setState(State.ACTIVE);
        profitRepository.save(profit);
        return profit;
    }

    public void delete(User user, int id) throws MoneyException {
        Profit profit = profitRepository.findByWalletUserIdAndId(user.getId(), id)
                .orElseThrow(() -> new MoneyException(MoneyErrorCode.PROFIT_NOT_EXIST));
        Wallet wallet = profit.getWallet();
        wallet.setBalance(wallet.getBalance().subtract(profit.getMoneyCount()));
        profit.setState(State.INACTIVE);
        profitRepository.save(profit);
        walletRepository.save(wallet);
    }
}
