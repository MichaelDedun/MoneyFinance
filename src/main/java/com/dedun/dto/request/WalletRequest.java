package com.dedun.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class WalletRequest {

    @Positive(message = "The value must be positive")
    private BigDecimal balance;
    @NotBlank
    private String name;


    public WalletRequest() {
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
