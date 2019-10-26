package com.dedun.exception;


public enum MoneyErrorCode {
    USER_EXIST("User already in data base"),
    USER_NOT_EXIST("User does not exist"),
    WALLET_NOT_EXIST("Wallet does not exist"),
    REPORT_NOT_EXIST("Report does not exist"),
    WALLET_OPERATIONS("Operations can not be applied"),
    PROFIT_NOT_EXIST("Profit does not exist"),
    LOSS_NOT_EXIST("Loss does not exist"),
    PAYMENT_NOT_EXIST("Payment does not exist"),
    WALLET_ALREADY_ACTIVE("Wallet is already active"),
    PROFIT_ALREADY_ACTIVE("Profit is already active"),
    PROFIT_NOT_ACTIVE("Profit not active"),
    LOSS_NOT_ACTIVE("Loss not active"),
    LOSS_ALREADY_ACTIVE("Loss is already active");
    private String errorCode;

    MoneyErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
