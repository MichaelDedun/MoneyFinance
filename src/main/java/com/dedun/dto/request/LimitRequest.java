package com.dedun.dto.request;

import java.math.BigDecimal;

public class LimitRequest {
    private BigDecimal moneyCount;
    private String dateFrom;
    private String dateTo;

    public LimitRequest() {
    }

    public LimitRequest(BigDecimal moneyCount, String dateFrom, String dateTo) {
        this.moneyCount = moneyCount;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public BigDecimal getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(BigDecimal moneyCount) {
        this.moneyCount = moneyCount;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
