package interfaces;

public interface LoanOperations {
    boolean payInstallment(double amount);
    void approveLoan();
    void rejectLoan();
    void activate();
}
