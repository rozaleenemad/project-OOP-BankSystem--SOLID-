package models;

import enums.AccountStatus;
import strategy.account.NoInterest;

public class CurrentAccount extends Account {

    private final double overdraftLimit;

    public CurrentAccount(int accountID, int customerID, String currency,double balance, double overdraftLimit) {
        super(accountID, customerID, "Current", currency, balance, null, AccountStatus.ACTIVE);
        setInterestStrategy(new NoInterest());
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) return false;
        if (getBalance() + overdraftLimit >= amount) {
            setBalance(getBalance() - amount);
            return true;
        }
        return false;
    }
}
