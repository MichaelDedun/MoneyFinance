package com.dedun.model;


import com.dedun.model.enums.State;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "profit")
public class Profit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal moneyCount;
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private State state;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @ManyToMany(mappedBy = "profits")
    private List<Report> reports;

    public Profit() {
    }

    public Profit(BigDecimal moneyCount, LocalDateTime date, Wallet wallet) {
        this.moneyCount = moneyCount;
        this.date = date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime date() {
        return date;
    }

    public void setTimeOfCreation(LocalDateTime date) {
        this.date = date;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profit)) return false;
        Profit profit = (Profit) o;
        return getId() == profit.getId() &&
                Objects.equals(getMoneyCount(), profit.getMoneyCount()) &&
                Objects.equals(date(), profit.date()) &&
                getState() == profit.getState() &&
                Objects.equals(getWallet(), profit.getWallet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMoneyCount(), date(), getState(), getWallet());
    }
}
