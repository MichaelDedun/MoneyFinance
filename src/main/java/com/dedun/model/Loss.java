package com.dedun.model;

import com.dedun.model.enums.State;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "loss")
public class Loss {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
    private BigDecimal moneyCount;
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private State state;

    @ManyToMany(mappedBy = "losses")
    private List<Report> reports;

    public Loss() {
    }

    public Loss(Wallet wallet, LocalDateTime date, BigDecimal moneyCount) {
        this.wallet = wallet;
        this.date = date;
        this.moneyCount = moneyCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loss)) return false;
        Loss loss = (Loss) o;
        return getId() == loss.getId() &&
                Objects.equals(getWallet(), loss.getWallet()) &&
                Objects.equals(getMoneyCount(), loss.getMoneyCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getWallet(), getMoneyCount());
    }
}
