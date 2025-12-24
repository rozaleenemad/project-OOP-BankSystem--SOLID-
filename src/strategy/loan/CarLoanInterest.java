package strategy.loan;

import models.Loan;

public class CarLoanInterest implements LoanInterestStrategy {
    @Override
    public double calculateInterest(Loan loan) {
        return loan.getAmount() * loan.getInterestRate() / 100 * 1.5;
    }
}
