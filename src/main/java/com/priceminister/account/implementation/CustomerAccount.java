package com.priceminister.account.implementation;

import com.priceminister.account.*;


public class CustomerAccount implements Account {

    private Double balance;

    public CustomerAccount() {
        this.balance = 0.0;
    }

    public void add(Double addedAmount) {
        if(null == addedAmount || addedAmount < 0) {
            throw new IllegalArgumentException("Adding the amount " + addedAmount + " is not permitted");
        }
        balance+= addedAmount;
    }

    public Double getBalance() {
        return balance;
    }

    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) 
    		throws IllegalBalanceException {

        if(null == withdrawnAmount || withdrawnAmount < 0) {
            throw new IllegalArgumentException("Withdrawing the amount " + withdrawnAmount + " is not permitted");
        }

        if(null == rule) {
            throw new IllegalArgumentException("The account rule must not be null when withdrawing");
        }

        Double newBalance = balance - withdrawnAmount;
        if(!rule.withdrawPermitted(newBalance)) {
            throw new IllegalBalanceException(newBalance);
        }

        balance = newBalance;
        return balance;
    }

}
