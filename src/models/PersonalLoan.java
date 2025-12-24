package models;

import java.util.Date;
import strategy.loan.PersonalLoanInterest;

public class PersonalLoan extends Loan {

    public PersonalLoan(int loanID, int customerID, double amount,
                        double interestRate, Date start, Date end) {

        super(loanID, customerID, amount, interestRate, start, end);
        this.interestStrategy = new PersonalLoanInterest();
    }
}
