package models;

import enums.AccountStatus;
import strategy.account.SavingInterest;

public class SavingAccount extends Account {

    public SavingAccount(int accountID, int customerID, String currency,double balance, double interestRate) {
        super(accountID, customerID, "Saving", currency, balance, null, AccountStatus.ACTIVE);
        setInterestStrategy(new SavingInterest(interestRate));
    }
}
