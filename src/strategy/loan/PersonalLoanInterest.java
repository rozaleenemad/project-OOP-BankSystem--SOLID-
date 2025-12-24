package strategy.loan;

import models.Loan;

public class PersonalLoanInterest implements LoanInterestStrategy {
    @Override
    public double calculateInterest(Loan loan) {
        return loan.getAmount() * loan.getInterestRate() / 100;
    }
}
