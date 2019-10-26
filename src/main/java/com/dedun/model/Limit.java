package com.dedun.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "\"limit\"")
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal moneyCount;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
    @ManyToMany(mappedBy = "limits")
    private List<Report> reports;

    public Limit() {
    }

    public Limit(BigDecimal moneyCount, LocalDateTime dateFrom, LocalDateTime dateTo, Wallet wallet) {
        this.moneyCount = moneyCount;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.wallet = wallet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(BigDecimal moneyCount) {
        this.moneyCount = moneyCount;
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDateTime getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDateTime dateTo) {
        this.dateTo = dateTo;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Limit)) return false;
        Limit limit = (Limit) o;
        return getId() == limit.getId() &&
                Objects.equals(getMoneyCount(), limit.getMoneyCount()) &&
                Objects.equals(getDateFrom(), limit.getDateFrom()) &&
                Objects.equals(getDateTo(), limit.getDateTo()) &&
                Objects.equals(getWallet(), limit.getWallet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMoneyCount(), getDateFrom(), getDateTo(), getWallet());
    }
}
