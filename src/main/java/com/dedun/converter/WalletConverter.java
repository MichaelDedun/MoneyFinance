package com.dedun.converter;

import com.dedun.dto.response.WalletResponse;
import com.dedun.model.Wallet;
import org.springframework.stereotype.Service;

@Service
public class WalletConverter extends MoneyEntityConverter<Wallet, WalletResponse> {
    public WalletConverter(LimitConverter limitConverter) {
        super(WalletResponse::new);
    }

    @Override
    protected WalletResponse inflateResponse(Wallet wallet, WalletResponse walletResponse) {
        return walletResponse
                .setId(wallet.getId())
                .setName(wallet.getName())
                .setBalance(wallet.getBalance());
    }
}
