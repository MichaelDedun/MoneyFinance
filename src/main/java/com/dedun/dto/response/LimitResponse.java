package com.dedun.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LimitResponse {
    private int id;
    private BigDecimal moneyCount;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;

    public LimitResponse() {
    }

    public int getId() {
        return id;
    }

    public LimitResponse setId(int id) {
        this.id = id;
        return this;
    }

    public BigDecimal getMoneyCount() {
        return moneyCount;
    }

    public LimitResponse setMoneyCount(BigDecimal moneyCount) {
        this.moneyCount = moneyCount;
        return this;
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public LimitResponse setDateFrom(LocalDateTime dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public LocalDateTime getDateTo() {
        return dateTo;
    }

    public LimitResponse setDateTo(LocalDateTime dateTo) {
        this.dateTo = dateTo;
        return this;
    }
}
