package com.dedun.dto.request;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ProfitRequest {


    @Positive(message = "The value must be positive")
    private BigDecimal moneyCount;
    private int walletId;

    public ProfitRequest() {
    }

    public ProfitRequest(BigDecimal moneyCount, int walletId) {
        this.moneyCount = moneyCount;
        this.walletId = walletId;
    }

    public BigDecimal getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(BigDecimal moneyCount) {
        this.moneyCount = moneyCount;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }
}
