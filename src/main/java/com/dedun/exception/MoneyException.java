package com.dedun.exception;


public class MoneyException extends Throwable {

    private MoneyErrorCode moneyErrorCode;

    public MoneyException(MoneyErrorCode moneyErrorCode) {
        this.moneyErrorCode = moneyErrorCode;
    }


    public MoneyErrorCode getMoneyErrorCode() {
        return moneyErrorCode;
    }

    public void setMoneyErrorCode(MoneyErrorCode moneyErrorCode) {
        this.moneyErrorCode = moneyErrorCode;
    }
}
