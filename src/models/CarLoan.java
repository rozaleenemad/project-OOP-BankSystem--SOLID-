package models;

import java.util.Date;
import strategy.loan.CarLoanInterest;

public class CarLoan extends Loan {
    public CarLoan(int loanID, int customerID, double amount, double interestRate,
                   Date startDate, Date endDate) {
        super(loanID, customerID, amount, interestRate, startDate, endDate);
        this.interestStrategy = new CarLoanInterest();
    }
}