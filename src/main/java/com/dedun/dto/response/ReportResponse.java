package com.dedun.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class ReportResponse {

    private int id;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private List<ProfitResponse> profits;
    private List<LossResponse> losses;
    private List<LimitResponse> limits;

    public ReportResponse() {
    }

    public int getId() {
        return id;
    }

    public ReportResponse setId(int id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public ReportResponse setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
        return this;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public ReportResponse setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }

    public List<ProfitResponse> getProfits() {
        return profits;
    }

    public ReportResponse setProfits(List<ProfitResponse> profits) {
        this.profits = profits;
        return this;
    }

    public List<LossResponse> getLosses() {
        return losses;
    }

    public ReportResponse setLosses(List<LossResponse> losses) {
        this.losses = losses;
        return this;
    }

    public List<LimitResponse> getLimits() {
        return limits;
    }

    public ReportResponse setLimits(List<LimitResponse> limits) {
        this.limits = limits;
        return this;
    }
}
