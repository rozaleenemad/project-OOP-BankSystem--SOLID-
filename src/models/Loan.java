package models;

import enums.LoanStatus;
import interfaces.LoanOperations;
import java.util.Date;
import strategy.loan.LoanInterestStrategy;

public abstract class Loan implements LoanOperations {

    protected int loanID;
    protected int customerID;
    protected double amount;
    protected double interestRate;
    protected Date startDate;
    protected Date endDate;
    protected LoanStatus status;

    protected LoanInterestStrategy interestStrategy;

    protected Loan(int loanID, int customerID, double amount,
                   double interestRate, Date startDate, Date endDate) {

        this.loanID = loanID;
        this.customerID = customerID;
        this.amount = amount;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = LoanStatus.PENDING;
    }

    public void applyInterest() {
        double interest = interestStrategy.calculateInterest(this);
        this.amount += interest;
    }

    @Override
    public boolean payInstallment(double value) {
        if (status != LoanStatus.ACTIVE || value <= 0 || value > amount)
            return false;

        amount -= value;
        if (amount == 0)
            status = LoanStatus.PAID;

        return true;
    }

    @Override
    public void approveLoan() {
        status = LoanStatus.APPROVED;
    }

    @Override
    public void rejectLoan() {
        status = LoanStatus.REJECTED;
    }

    @Override
    public void activate() {
        if (status == LoanStatus.APPROVED)
            status = LoanStatus.ACTIVE;
    }

    public double getAmount() { return amount; }
    public double getInterestRate() { return interestRate; }
    public LoanStatus getStatus() { return status; }

   public void setStatus(LoanStatus status) {
    this.status = status;
}


   
    

}
