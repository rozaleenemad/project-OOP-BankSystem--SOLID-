package models;

import java.util.Date;
import strategy.loan.MortgageLoanInterest;

public class MortgageLoan extends Loan {
    public MortgageLoan(int loanID, int customerID, double amount, double interestRate,
                        Date startDate, Date endDate) {
        super(loanID, customerID, amount, interestRate, startDate, endDate);
        this.interestStrategy = new MortgageLoanInterest();
    }
}