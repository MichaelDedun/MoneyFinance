package com.dedun.dto.response;

import java.math.BigDecimal;
import java.util.List;

public class WalletResponse {

    private int id;
    private String name;
    private BigDecimal balance;

    public WalletResponse() {
    }

    public int getId() {
        return id;
    }

    public WalletResponse setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public WalletResponse setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public WalletResponse setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }
}
