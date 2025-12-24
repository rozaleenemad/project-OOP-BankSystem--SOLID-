package builder.loan;

import java.util.Date;
import models.PersonalLoan;

public class PersonalLoanBuilder {
    private int loanID;
    private int customerID;
    private double amount;
    private double interestRate;
    private Date startDate;
    private Date endDate;

    public PersonalLoanBuilder setLoanID(int id) { 
        this.loanID = id; return this; 
    }
    public PersonalLoanBuilder setCustomerID(int id) { 
        this.customerID = id; return this; 
    }
    public PersonalLoanBuilder setAmount(double a) { 
        this.amount = a; return this; 
    }
    public PersonalLoanBuilder setInterestRate(double r) { 
        this.interestRate = r; return this; 
    }
    public PersonalLoanBuilder setStartDate(Date start) { 
        this.startDate = start; return this; 
    }
    public PersonalLoanBuilder setEndDate(Date end) { 
        this.endDate = end; return this; 
    }

    public PersonalLoan build() {
        return new PersonalLoan(loanID, customerID, amount, interestRate, startDate, endDate);
    }
}
