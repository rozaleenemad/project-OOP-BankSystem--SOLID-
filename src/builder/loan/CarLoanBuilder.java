package builder.loan;

import models.CarLoan;
import java.util.Date;

public class CarLoanBuilder {
    private int loanID;
    private int customerID;
    private double amount;
    private double interestRate;
    private Date startDate;
    private Date endDate;

    public CarLoanBuilder setLoanID(int id) { 
        this.loanID = id; return this; 
    }
    public CarLoanBuilder setCustomerID(int id) { 
        this.customerID = id; return this; 
    }
    public CarLoanBuilder setAmount(double a) { 
        this.amount = a; return this; 
    }
    public CarLoanBuilder setInterestRate(double r) { 
        this.interestRate = r; return this; 
    }
    public CarLoanBuilder setStartDate(Date start) { 
        this.startDate = start; return this; 
    }
    public CarLoanBuilder setEndDate(Date end) { 
        this.endDate = end; return this; 
    }

    public CarLoan build() {
        return new CarLoan (loanID, customerID, amount, interestRate, startDate, endDate);
    }
}
