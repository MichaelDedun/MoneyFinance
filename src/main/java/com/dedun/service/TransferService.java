package com.dedun.service;

import com.dedun.dto.request.TransferRequest;
import com.dedun.exception.MoneyErrorCode;
import com.dedun.exception.MoneyException;
import com.dedun.model.Payment;
import com.dedun.model.Transfer;
import com.dedun.model.User;
import com.dedun.model.Wallet;
import com.dedun.model.enums.PaymentType;
import com.dedun.repository.PaymentRepository;
import com.dedun.repository.TransferRepository;
import com.dedun.repository.WalletRepository;
import com.dedun.validator.TransferValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransferService {
    private final WalletRepository walletRepository;
    private final TransferRepository transferRepository;
    private final PaymentRepository paymentRepository;
    private final TransferValidator transferValidator;

    public TransferService(WalletRepository walletRepository,
                           TransferRepository transferRepository,
                           PaymentRepository paymentRepository,
                           TransferValidator transferValidator) {
        this.walletRepository = walletRepository;
        this.transferRepository = transferRepository;
        this.paymentRepository = paymentRepository;
        this.transferValidator = transferValidator;
    }

    public Transfer saveTransfer(User user, TransferRequest request) throws MoneyException {
        Wallet sourceWallet = walletRepository.findByUserIdAndId(user.getId(), request.getSourceWalletId())
                .orElseThrow(() -> new MoneyException(MoneyErrorCode.WALLET_NOT_EXIST));
        Wallet goalWallet = walletRepository.findByUserIdAndId(user.getId(), request.getGoalWalletId())
                .orElseThrow(() -> new MoneyException(MoneyErrorCode.WALLET_NOT_EXIST));
        transferValidator.checkSourceWalletBalance(sourceWallet, request);
        sourceWallet.setBalance(sourceWallet.getBalance().subtract(request.getMoneyCount()));
        goalWallet.setBalance(goalWallet.getBalance().add(request.getMoneyCount()));
        walletRepository.save(sourceWallet);
        walletRepository.save(goalWallet);
        Transfer transfer = new Transfer(sourceWallet, goalWallet, request.getMoneyCount(), LocalDateTime.now());
        transferRepository.save(transfer);
        Payment paymentSource = new Payment(transfer.getId(), LocalDateTime.now(), request.getMoneyCount(), sourceWallet);
        paymentSource.setType(PaymentType.TRANSFER_SOURCE);
        paymentRepository.save(paymentSource);
        Payment paymentGoal = new Payment(transfer.getId(), transfer.getDate(), request.getMoneyCount(), goalWallet);
        paymentGoal.setType(PaymentType.TRANSFER_GOAL);
        paymentRepository.save(paymentGoal);
        return transfer;
    }
}
