package com.dedun.service;

import com.dedun.dto.request.LossRequest;
import com.dedun.exception.MoneyErrorCode;
import com.dedun.exception.MoneyException;
import com.dedun.model.Loss;
import com.dedun.model.User;
import com.dedun.model.Wallet;
import com.dedun.model.enums.State;
import com.dedun.repository.LossRepository;
import com.dedun.repository.WalletRepository;
import com.dedun.validator.LossValidator;
import com.dedun.validator.WalletValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LossService {
    private final LossRepository lossRepository;
    private final WalletRepository walletRepository;
    private final WalletValidator walletValidator;
    private final LossValidator lossValidator;
    private final PaymentService paymentService;

    public LossService(LossRepository lossRepository,
                       WalletRepository walletRepository,
                       WalletValidator walletValidator,
                       LossValidator lossValidator,
                       PaymentService paymentService) {
        this.lossRepository = lossRepository;
        this.walletRepository = walletRepository;
        this.walletValidator = walletValidator;
        this.lossValidator = lossValidator;
        this.paymentService = paymentService;
    }

    public Loss saveLoss(LossRequest request, User user) throws MoneyException {
        Wallet wallet = walletRepository.findByUserIdAndId(user.getId(), request.getWalletId())
                .orElseThrow(() -> new MoneyException(MoneyErrorCode.WALLET_NOT_EXIST));
        walletValidator.checkWalletBalanceBiggerThenLossrequestMoneyCount(wallet, request);
        wallet.setBalance(wallet.getBalance().subtract(request.getMoneyCount()));
        walletRepository.save(wallet);
        Loss loss = new Loss(wallet, LocalDateTime.now(), request.getMoneyCount());
        loss.setState(State.ACTIVE);
        lossRepository.save(loss);
        paymentService.save(loss);
        return loss;
    }

    public List<Loss> getAll(int id) {
        return lossRepository.findByWalletId(id)
                .stream()
                .filter(loss -> loss.getState().equals(State.ACTIVE))
                .collect(Collectors.toList());
    }

    public Loss get(User user, int id) throws MoneyException {
        return lossRepository.findByWalletUserIdAndId(user.getId(), id)
                .orElseThrow(() -> new MoneyException(MoneyErrorCode.LOSS_NOT_EXIST));
    }

    public Loss edit(LossRequest lossRequest, User user, int id) throws MoneyException {
        Loss loss = lossRepository.findByWalletUserIdAndId(user.getId(), id)
                .orElseThrow(() -> new MoneyException(MoneyErrorCode.LOSS_NOT_EXIST));
        Wallet wallet = loss.getWallet();
        walletValidator.checkWalletBalanceBiggerThenLossMoneyCount(wallet, loss, lossRequest);
        wallet.setBalance(wallet.getBalance().add(loss.getMoneyCount()));
        loss.setMoneyCount(lossRequest.getMoneyCount());
        wallet.setBalance(wallet.getBalance().subtract(loss.getMoneyCount()));
        walletRepository.save(wallet);
        lossRepository.save(loss);
        paymentService.edit(loss);
        return loss;
    }

    public Loss updateState(User user, int id) throws MoneyException {
        Loss loss = lossRepository.findByWalletUserIdAndId(user.getId(), id)
                .orElseThrow(() -> new MoneyException(MoneyErrorCode.LOSS_NOT_EXIST));
        lossValidator.checkLossIsActive(loss);
        loss.setState(State.ACTIVE);
        lossRepository.save(loss);
        return loss;
    }

    public void delete(User user, int id) throws MoneyException {
        Loss loss = lossRepository.findByWalletUserIdAndId(user.getId(), id)
                .orElseThrow(() -> new MoneyException(MoneyErrorCode.LOSS_NOT_EXIST));
        Wallet wallet = loss.getWallet();
        wallet.setBalance(wallet.getBalance().add(loss.getMoneyCount()));
        loss.setState(State.INACTIVE);
        walletRepository.save(wallet);
        lossRepository.save(loss);
    }
}
