package services;

import java.util.ArrayList;
import java.util.List;
import models.Loan;

public class LoanService {

    private final List<Loan> loans = new ArrayList<>();

    // إضافة قرض للنظام
    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    // الحصول على كل القروض
    public List<Loan> getAllLoans() {
        return loans;
    }

    // دفع قسط من القرض
    public boolean payInstallment(Loan loan, double amount) {
        return loan.payInstallment(amount);
    }

    // تفعيل القرض بعد الموافقة
    public void activateLoan(Loan loan) {
        loan.activate();
    }

    // الموافقة على القرض
    public void approveLoan(Loan loan) {
        loan.approveLoan();
    }

    // رفض القرض
    public void rejectLoan(Loan loan) {
        loan.rejectLoan();
    }

    // تطبيق الفائدة على القرض
    public void applyInterest(Loan loan) {
        loan.applyInterest();
    }
}
