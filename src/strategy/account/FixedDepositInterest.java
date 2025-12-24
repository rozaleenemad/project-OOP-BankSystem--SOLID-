package strategy.account;

import models.Account;
import models.FixedDepositAccount;

public class FixedDepositInterest implements InterestStrategy {

    private final double interestRate;
    private final int durationMonths;

    public FixedDepositInterest(double interestRate, int durationMonths) {
        this.interestRate = interestRate;
        this.durationMonths = durationMonths;
    }

    @Override
    public void calculateInterest(Account account) {
        if (account instanceof FixedDepositAccount fd) {
            double interest = fd.getBalance() * interestRate * durationMonths / 1200;
            fd.setBalance(fd.getBalance() + interest);
        }
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }
}
