package com.dedun.model;

import com.dedun.model.enums.State;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal balance;
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private State state;


    @OneToMany(mappedBy = "wallet", cascade = CascadeType.REMOVE)
    private List<Payment> payments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.REMOVE)
    private List<Profit> profits;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.REMOVE)
    private List<Loss> losses;

    @ManyToMany(mappedBy = "sourceWallet", cascade = CascadeType.REMOVE)
    private List<Transfer> transfers;

    @ManyToMany(mappedBy = "goalWallet", cascade = CascadeType.REMOVE)
    private List<Transfer> transferss;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.REMOVE)
    private List<Limit> limits;


    public Wallet() {
    }

    public Wallet(User user, BigDecimal balance, String name) {
        this.user = user;
        this.name = name;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Profit> getProfits() {
        return profits;
    }

    public void setProfits(List<Profit> profits) {
        this.profits = profits;
    }

    public List<Loss> getLosses() {
        return losses;
    }

    public void setLosses(List<Loss> losses) {
        this.losses = losses;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<Transfer> transfers) {
        this.transfers = transfers;
    }

    public List<Transfer> getTransferss() {
        return transferss;
    }

    public void setTransferss(List<Transfer> transferss) {
        this.transferss = transferss;
    }

    public List<Limit> getLimits() {
        return limits;
    }

    public void setLimits(List<Limit> limits) {
        this.limits = limits;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return id == wallet.id &&
                Objects.equals(balance, wallet.balance) &&
                Objects.equals(name, wallet.name) &&
                state == wallet.state &&
                Objects.equals(user, wallet.user) &&
                Objects.equals(profits, wallet.profits) &&
                Objects.equals(losses, wallet.losses) &&
                Objects.equals(transfers, wallet.transfers) &&
                Objects.equals(transferss, wallet.transferss) &&
                Objects.equals(limits, wallet.limits) &&
                Objects.equals(payments, wallet.payments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, name, state, user, profits, losses, transfers, transferss, limits, payments);
    }
}
