package com.dedun.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class TransferRequest {

    @Positive(message = "The value must be positive")
    private BigDecimal moneyCount;
    @NotNull
    private Integer sourceWalletId;
    @NotNull
    private Integer goalWalletId;

    public TransferRequest() {
    }

    public TransferRequest(BigDecimal moneyCount, Integer sourceWalletId, Integer goalWalletId) {
        this.moneyCount = moneyCount;
        this.sourceWalletId = sourceWalletId;
        this.goalWalletId = goalWalletId;
    }

    public BigDecimal getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(BigDecimal moneyCount) {
        this.moneyCount = moneyCount;
    }

    public Integer getSourceWalletId() {
        return sourceWalletId;
    }

    public void setSourceWalletId(Integer sourceWalletId) {
        this.sourceWalletId = sourceWalletId;
    }

    public Integer getGoalWalletId() {
        return goalWalletId;
    }

    public void setGoalWalletId(Integer goalWalletId) {
        this.goalWalletId = goalWalletId;
    }
}
