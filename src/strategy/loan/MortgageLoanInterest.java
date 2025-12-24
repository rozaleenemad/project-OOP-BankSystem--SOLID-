package strategy.loan;

import models.Loan;

public class MortgageLoanInterest implements LoanInterestStrategy {
    @Override
    public double calculateInterest(Loan loan) {
        return loan.getAmount() * loan.getInterestRate() / 100 * 0.9;
    }
}
