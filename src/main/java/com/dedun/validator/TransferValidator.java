package com.dedun.validator;

import com.dedun.dto.request.TransferRequest;
import com.dedun.exception.MoneyErrorCode;
import com.dedun.exception.MoneyException;
import com.dedun.model.Wallet;
import org.springframework.stereotype.Service;

@Service
public class TransferValidator {
    public void checkSourceWalletBalance(Wallet sourceWallet, TransferRequest request) throws MoneyException {
        if (sourceWallet.getBalance().compareTo(request.getMoneyCount()) < 0)
            throw new MoneyException(MoneyErrorCode.WALLET_OPERATIONS);
    }
}
