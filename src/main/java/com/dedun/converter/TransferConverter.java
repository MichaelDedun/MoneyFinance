package com.dedun.converter;

import com.dedun.dto.response.TransferResponse;
import com.dedun.model.Transfer;
import org.springframework.stereotype.Service;

@Service
public class TransferConverter extends MoneyEntityConverter<Transfer, TransferResponse> {
    private final WalletConverter walletConverter;

    public TransferConverter(WalletConverter walletConverter) {
        super(TransferResponse::new);
        this.walletConverter = walletConverter;
    }

    @Override
    protected TransferResponse inflateResponse(Transfer transfer, TransferResponse transferResponse) {
        return transferResponse
                .setId(transfer.getId())
                .setMoneyCount(transfer.getMoneyCount())
                .setSourceWallet(walletConverter.from(transfer.getSourceWallet()))
                .setGoalWallet(walletConverter.from(transfer.getGoalWallet()))
                .setDate(transfer.getDate());
    }
}
