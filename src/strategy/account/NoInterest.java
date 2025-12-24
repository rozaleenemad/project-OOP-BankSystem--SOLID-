package strategy.account;

import models.Account;

public class NoInterest implements InterestStrategy {

    @Override
    public void calculateInterest(Account account) {
        // لا فائدة
    }

    @Override
    public double getInterestRate() {
        return 0;
    }
}
