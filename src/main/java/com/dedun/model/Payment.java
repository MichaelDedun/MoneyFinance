package com.dedun.model;

import com.dedun.model.enums.PaymentType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private PaymentType type;
    private Integer goalId;
    private LocalDateTime date;
    private BigDecimal moneyCount;

    @OneToMany(mappedBy = "parentPayment")
    private List<Payment> correctingPayments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Payment parentPayment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    private Wallet  wallet;

    public Payment() {
    }

    public Payment(Integer goalId, LocalDateTime date, BigDecimal moneyCount, Wallet wallet) {
        this.goalId = goalId;
        this.date = date;
        this.moneyCount = moneyCount;
        this.wallet = wallet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public Integer getGoalId() {
        return goalId;
    }

    public void setGoalId(Integer goalId) {
        this.goalId = goalId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(BigDecimal moneyCount) {
        this.moneyCount = moneyCount;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public List<Payment> getCorrectingPayments() {
        return correctingPayments;
    }

    public void setCorrectingPayments(List<Payment> correctingPayments) {
        this.correctingPayments = correctingPayments;
    }

    public Payment getParentPayment() {
        return parentPayment;
    }

    public void setParentPayment(Payment parentPayment) {
        this.parentPayment = parentPayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return getId() == payment.getId() &&
                getType() == payment.getType() &&
                Objects.equals(getGoalId(), payment.getGoalId()) &&
                Objects.equals(getDate(), payment.getDate()) &&
                Objects.equals(getMoneyCount(), payment.getMoneyCount()) &&
                Objects.equals(getCorrectingPayments(), payment.getCorrectingPayments()) &&
                Objects.equals(getParentPayment(), payment.getParentPayment()) &&
                Objects.equals(getWallet(), payment.getWallet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getGoalId(), getDate(), getMoneyCount(), getCorrectingPayments(), getParentPayment(), getWallet());
    }
}
