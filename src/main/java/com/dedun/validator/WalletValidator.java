package com.dedun.validator;

import com.dedun.dto.request.LossRequest;
import com.dedun.exception.MoneyErrorCode;
import com.dedun.exception.MoneyException;
import com.dedun.model.Loss;
import com.dedun.model.Profit;
import com.dedun.model.Wallet;
import com.dedun.model.enums.State;
import org.springframework.stereotype.Service;

@Service
public class WalletValidator {
    public void checkWalletIsActive(Wallet wallet) throws MoneyException {
        if (wallet.getState().equals(State.ACTIVE))
            throw new MoneyException(MoneyErrorCode.WALLET_ALREADY_ACTIVE);
    }

    public void checkWalletBalanceBiggerThenProfitBalance(Wallet wallet, Profit profit) throws MoneyException {
        if (wallet.getBalance().compareTo(profit.getMoneyCount()) < 0)
            throw new MoneyException(MoneyErrorCode.WALLET_OPERATIONS);
    }

    public void checkWalletBalanceBiggerThenLossrequestMoneyCount(Wallet wallet, LossRequest request) throws MoneyException {
        if (wallet.getBalance().compareTo(request.getMoneyCount()) < 0)
            throw new MoneyException(MoneyErrorCode.WALLET_OPERATIONS);
    }

    public void checkWalletBalanceBiggerThenLossMoneyCount(Wallet wallet ,Loss loss, LossRequest lossRequest) throws MoneyException {
        if (wallet.getBalance().add(loss.getMoneyCount()).compareTo(lossRequest.getMoneyCount()) < 0)
            throw new MoneyException(MoneyErrorCode.WALLET_OPERATIONS);
    }
}
