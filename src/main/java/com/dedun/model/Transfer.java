package com.dedun.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Transfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "source_wallet")
    private Wallet sourceWallet;

    @ManyToOne
    @JoinColumn(name = "goal_wallet")
    private Wallet goalWallet;

    private BigDecimal moneyCount;
    private LocalDateTime date;

    public Transfer() {
    }

    public Transfer(Wallet sourceWallet, Wallet goalWallet, BigDecimal moneyCount, LocalDateTime date) {
        this.sourceWallet = sourceWallet;
        this.goalWallet = goalWallet;
        this.moneyCount = moneyCount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Wallet getSourceWallet() {
        return sourceWallet;
    }

    public void setSourceWallet(Wallet sourceWallet) {
        this.sourceWallet = sourceWallet;
    }

    public Wallet getGoalWallet() {
        return goalWallet;
    }

    public void setGoalWallet(Wallet goalWallet) {
        this.goalWallet = goalWallet;
    }

    public BigDecimal getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(BigDecimal moneyCount) {
        this.moneyCount = moneyCount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transfer transfer = (Transfer) o;
        return id == transfer.id &&
                Objects.equals(sourceWallet, transfer.sourceWallet) &&
                Objects.equals(goalWallet, transfer.goalWallet) &&
                Objects.equals(moneyCount, transfer.moneyCount) &&
                Objects.equals(date, transfer.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sourceWallet, goalWallet, moneyCount, date);
    }
}
