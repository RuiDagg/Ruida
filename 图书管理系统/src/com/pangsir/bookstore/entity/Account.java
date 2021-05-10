package com.pangsir.bookstore.entity;

/**
 * 账户类
 */
public class Account {

    private Integer accountId;
    private double balance;//账户余额

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                '}';
    }

    public Account() {
    }

    public Account(Integer accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
