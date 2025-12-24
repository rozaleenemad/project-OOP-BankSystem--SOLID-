package models;

import enums.AccountStatus;
import strategy.account.FixedDepositInterest;

public class FixedDepositAccount extends Account {

    private final int durationMonths;

    public FixedDepositAccount(int accountID, int customerID, String currency,double balance, double interestRate, int durationMonths) {
        super(accountID, customerID, "Fixed", currency, balance, null, AccountStatus.ACTIVE);
        this.durationMonths = durationMonths;
        setInterestStrategy(new FixedDepositInterest(interestRate, durationMonths));
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    @Override
    public boolean withdraw(double amount) {
    System.out.println("ل يمكن سحب أي مبلغ من الحساب قبل انتهاء المدة");
        return false;
    }
}
