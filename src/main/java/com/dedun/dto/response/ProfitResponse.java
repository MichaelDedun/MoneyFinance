package com.dedun.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProfitResponse {
    private int id;
    private BigDecimal moneyCount;
    private LocalDateTime date;

    public ProfitResponse() {
    }

    public int getId() {
        return id;
    }

    public ProfitResponse setId(int id) {
        this.id = id;
        return this;
    }

    public BigDecimal getMoneyCount() {
        return moneyCount;
    }

    public ProfitResponse setMoneyCount(BigDecimal moneyCount) {
        this.moneyCount = moneyCount;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public ProfitResponse setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }
}
