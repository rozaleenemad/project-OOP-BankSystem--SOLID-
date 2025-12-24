package strategy.account;

import models.Account;

public interface InterestStrategy {
    void calculateInterest(Account account);
    double getInterestRate();
}
