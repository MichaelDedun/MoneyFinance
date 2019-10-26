package com.dedun.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferResponse {

    private int id;
    private BigDecimal moneyCount;
    private WalletResponse sourceWallet;
    private WalletResponse goalWallet;
    private LocalDateTime date;

    public TransferResponse() {
    }

    public int getId() {
        return id;
    }

    public TransferResponse setId(int id) {
        this.id = id;
        return this;
    }

    public BigDecimal getMoneyCount() {
        return moneyCount;
    }

    public TransferResponse setMoneyCount(BigDecimal moneyCount) {
        this.moneyCount = moneyCount;
        return this;
    }

    public WalletResponse getSourceWallet() {
        return sourceWallet;
    }

    public TransferResponse setSourceWallet(WalletResponse sourceWallet) {
        this.sourceWallet = sourceWallet;
        return this;
    }

    public WalletResponse getGoalWallet() {
        return goalWallet;
    }

    public TransferResponse setGoalWallet(WalletResponse goalWallet) {
        this.goalWallet = goalWallet;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public TransferResponse setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }
}
