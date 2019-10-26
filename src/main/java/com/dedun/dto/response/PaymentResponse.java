package com.dedun.dto.response;

import com.dedun.model.enums.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PaymentResponse {

    private int id;
    private LocalDateTime date;
    private BigDecimal moneyCount;
    private int goalId;
    private PaymentType type;
    private List<PaymentResponse> correctingPayments;

    public PaymentResponse() {
    }

    public int getId() {
        return id;
    }

    public PaymentResponse setId(int id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public PaymentResponse setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public BigDecimal getMoneyCount() {
        return moneyCount;
    }

    public PaymentResponse setMoneyCount(BigDecimal moneyCount) {
        this.moneyCount = moneyCount;
        return this;
    }

    public int getGoalId() {
        return goalId;
    }

    public PaymentResponse setGoalId(int goalId) {
        this.goalId = goalId;
        return this;
    }

    public PaymentType getType() {
        return type;
    }

    public PaymentResponse setType(PaymentType type) {
        this.type = type;
        return this;
    }

    public List<PaymentResponse> getCorrectingPayments() {
        return correctingPayments;
    }

    public PaymentResponse setCorrectingPayments(List<PaymentResponse> correctingPayments) {
        this.correctingPayments = correctingPayments;
        return this;
    }
}
