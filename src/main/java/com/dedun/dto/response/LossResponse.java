package com.dedun.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LossResponse {
    private int id;
    private BigDecimal moneyCount;
    private LocalDateTime date;

    public LossResponse() {
    }

    public int getId() {
        return id;
    }

    public LossResponse setId(int id) {
        this.id = id;
        return this;
    }

    public BigDecimal getMoneyCount() {
        return moneyCount;
    }

    public LossResponse setMoneyCount(BigDecimal moneyCount) {
        this.moneyCount = moneyCount;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public LossResponse setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }
}
