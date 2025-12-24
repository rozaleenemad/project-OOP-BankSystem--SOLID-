package models;

import enums.AccountStatus;
import interfaces.AccountOperations;
import java.util.Date;
import strategy.account.InterestStrategy;

public abstract class Account implements AccountOperations {

    private int accountID;
    private int customerID;
    private String accountType;
    private String currency;
    private double balance;
    private Date openDate;
    private AccountStatus status;

    protected InterestStrategy interestStrategy;

    public Account() {}

    public Account(int accountID, int customerID, String accountType, String currency,
                   double balance, Date openDate, AccountStatus status) {
        this.accountID = accountID;
        this.customerID = customerID;
        this.accountType = accountType;
        this.currency = currency;
        this.balance = balance;
        this.openDate = openDate;
        this.status = status;
    }

    // ------------ Getters  ------------
    public int getAccountID() { 
        return accountID; 
    }

    public int getCustomerID() {
         return customerID;
    }

    public String getAccountType() { 
        return accountType;
    }

    public String getCurrency() { 
        return currency;
    }

    public double getBalance() {
         return balance; 
    }
    public Date getOpenDate() { 
        return openDate; 
    }
    public AccountStatus getStatus() { 
        return status; 
    }

    // ------------Setters------------
    public void setAccountID(int id) { 
        this.accountID = id; 
    }
    public void setCustomerID(int id) { 
        this.customerID = id; 
    }
    public void setAccountType(String type) { 
        this.accountType = type; 
    }
    public void setCurrency(String currency) { 
        this.currency = currency; 
    }
    public void setBalance(double balance) { 
        this.balance = balance; 
    }
    public void setOpenDate(Date date) { 
        this.openDate = date; 
    }
    public void setStatus(AccountStatus status) { 
        this.status = status; 
    }

    // ------------ Operations ------------
    @Override
    public boolean deposit(double amount) {
        if (amount <= 0) return false;
        if (status != AccountStatus.ACTIVE) return false;
        balance += amount;
        return true;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) return false;
        if (status != AccountStatus.ACTIVE) return false;
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public boolean transfer(double amount, Account to) {
        if (amount <= 0) return false;
        if (status != AccountStatus.ACTIVE || to.status != AccountStatus.ACTIVE)
            return false;

        if (!withdraw(amount)) return false;
        if (!to.deposit(amount)) {
            deposit(amount); // rollback
            return false;
        }
        return true;
    }

    // ------------ Interest Strategy ------------
    public void setInterestStrategy(InterestStrategy strategy) {
        this.interestStrategy = strategy;
    }

    public void applyInterest() {
        if (interestStrategy != null) {
            interestStrategy.calculateInterest(this);
        }
    }

    public double getInterestRate() {
        if (interestStrategy != null) {
            return interestStrategy.getInterestRate();
        }
        return 0;
    }
}
