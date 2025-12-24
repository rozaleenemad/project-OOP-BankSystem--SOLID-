package builder.loan;

import java.util.Date;

import models.MortgageLoan;

public class MortgageLoanBuilder {
    private int loanID;
    private int customerID;
    private double amount;
    private double interestRate;
    private Date startDate;
    private Date endDate;

    public MortgageLoanBuilder setLoanID(int id) { 
        this.loanID = id; return this; 
    }
    public MortgageLoanBuilder setCustomerID(int id) { 
        this.customerID = id; return this; 
    }
    public MortgageLoanBuilder setAmount(double a) { 
        this.amount = a; return this; 
    }
    public MortgageLoanBuilder setInterestRate(double r) { 
        this.interestRate = r; return this; 
    }
    public MortgageLoanBuilder setStartDate(Date start) { 
        this.startDate = start; return this; 
    }
    public MortgageLoanBuilder setEndDate(Date end) { 
        this.endDate = end; return this; 
    }

    public MortgageLoan build() {
        return new MortgageLoan(loanID, customerID, amount, interestRate, startDate, endDate);
    }
}
