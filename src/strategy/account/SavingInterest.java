package strategy.account;

import models.Account;
import models.SavingAccount;

public class SavingInterest implements InterestStrategy {

    private final double interestRate;

    public SavingInterest(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public void calculateInterest(Account account) {
        if (account instanceof SavingAccount) {
            double interest = account.getBalance() * interestRate / 100;
            account.setBalance(account.getBalance() + interest);
        }
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }
}
