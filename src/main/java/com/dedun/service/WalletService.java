package com.dedun.service;

import com.dedun.dto.request.WalletRequest;
import com.dedun.exception.MoneyErrorCode;
import com.dedun.exception.MoneyException;
import com.dedun.model.User;
import com.dedun.model.Wallet;
import com.dedun.model.enums.State;
import com.dedun.repository.WalletRepository;
import com.dedun.validator.WalletValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final WalletValidator walletValidator;

    public WalletService(WalletRepository walletRepository, WalletValidator walletValidator) {
        this.walletRepository = walletRepository;
        this.walletValidator = walletValidator;
    }

    public Wallet save(WalletRequest walletRequest, User user) {
        Wallet wallet = new Wallet(user, walletRequest.getBalance(), walletRequest.getName());
        wallet.setState(State.ACTIVE);
        walletRepository.save(wallet);
        return wallet;
    }


    public List<Wallet> getAll(int id) {
        return walletRepository.findAllByUserId(id);
    }

    public Wallet get(User user, int id) throws MoneyException {
        return walletRepository.findByUserIdAndId(user.getId(), id)
                .orElseThrow(() -> new MoneyException(MoneyErrorCode.WALLET_NOT_EXIST));
    }

    public Wallet edit(WalletRequest walletRequest, User user, int id) throws MoneyException {
        Wallet wallet = walletRepository.findByUserIdAndId(user.getId(), id)
                .orElseThrow(() -> new MoneyException(MoneyErrorCode.WALLET_NOT_EXIST));
        wallet.setBalance(walletRequest.getBalance());
        wallet.setName(walletRequest.getName());
        walletRepository.save(wallet);
        return wallet;
    }

    public Wallet updateState(User user, int id) throws MoneyException {
        Wallet wallet = walletRepository.findByUserIdAndId(user.getId(), id)
                .orElseThrow(() -> new MoneyException(MoneyErrorCode.WALLET_NOT_EXIST));
        walletValidator.checkWalletIsActive(wallet);
        wallet.setState(State.ACTIVE);
        walletRepository.save(wallet);
        return wallet;
    }

    public void deleteAll(User user) {
        List<Wallet> wallets = walletRepository.findAllByUserId(user.getId()).stream()
                .filter(wallet -> wallet.getUser().getId().equals(user.getId()))
                .peek(wallet -> wallet.setState(State.INACTIVE))
                .collect(Collectors.toList());
        walletRepository.saveAll(wallets);
    }

    public void delete(User user, int id) throws MoneyException {
        Wallet wallet = walletRepository.findByUserIdAndId(user.getId(), id)
                .orElseThrow(() -> new MoneyException(MoneyErrorCode.WALLET_NOT_EXIST));
        wallet.setState(State.INACTIVE);
        walletRepository.save(wallet);
    }
}
