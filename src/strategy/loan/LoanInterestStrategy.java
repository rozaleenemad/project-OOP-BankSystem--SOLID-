package strategy.loan;

import models.Loan;

public interface LoanInterestStrategy {
    double calculateInterest(Loan loan);
}
