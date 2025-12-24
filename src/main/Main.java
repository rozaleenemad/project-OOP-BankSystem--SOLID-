package main;

import builder.account.CurrentAccountBuilder;
import builder.account.FixedDepositAccountBuilder;
import builder.account.SavingAccountBuilder;
import builder.card.CreditCardBuilder;
import builder.card.DebitCardBuilder;
import builder.loan.CarLoanBuilder;
import builder.loan.MortgageLoanBuilder;
import builder.loan.PersonalLoanBuilder;
import enums.CardProvider;
import java.util.Date;
import models.*;
import services.LoanService;
import strategy.account.FixedDepositInterest;
import strategy.account.NoInterest;
import strategy.account.SavingInterest;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== BUILDER + POLYMORPHISM + STRATEGY EXAMPLE ===");

        // =================== إنشاء الحسابات باستخدام Builder ===================
        SavingAccount acc1 = new SavingAccountBuilder()
                .setAccountID(1)
                .setCustomerID(101)
                .setCurrency("USD")
                .setBalance(1000)
                .setInterestRate(5)
                .build();

        CurrentAccount acc2 = new CurrentAccountBuilder()
                .setAccountID(2)
                .setCustomerID(102)
                .setCurrency("USD")
                .setBalance(500)
                .setOverdraftLimit(1000)
                .build();

        FixedDepositAccount acc3 = new FixedDepositAccountBuilder()
                .setAccountID(3)
                .setCustomerID(103)
                .setCurrency("USD")
                .setBalance(2000)
                .setInterestRate(7)
                .setDurationMonths(12)
                .build();

        Account[] accounts = { acc1, acc2, acc3 };

        // =================== التعامل مع الفائدة ===================
        System.out.println("\n--- Deposit 100 to all accounts ---");
        for (Account a : accounts) {
            a.deposit(100);
            System.out.println(a.getAccountType() + " balance: " + a.getBalance());
            a.applyInterest();
            System.out.println(a.getAccountType() + " balance after interest: " + a.getBalance());
        }

        // =================== عرض Overdraft للحساب الجاري ===================
        for (Account a : accounts) {
            if (a instanceof CurrentAccount ca) {
                System.out.println(a.getAccountType() + " Overdraft Limit: " + ca.getOverdraftLimit());
            }
        }

        // =================== Transfer Example ===================
        acc1.transfer(200, acc2);
        System.out.println("\n--- After Transfer ---");
        System.out.println(acc1.getAccountType() + " balance: " + acc1.getBalance());
        System.out.println(acc2.getAccountType() + " balance: " + acc2.getBalance());

        // =================== Strategy Example ===================
        acc2.setInterestStrategy(new SavingInterest(0.03));
        acc2.applyInterest();
        System.out.println(acc2.getAccountType() + " balance after new interest: " + acc2.getBalance());

        acc1.setInterestStrategy(new FixedDepositInterest(0.06, 12));
        acc1.applyInterest();
        System.out.println(acc1.getAccountType() + " balance after fixed deposit interest: " + acc1.getBalance());

        acc2.setInterestStrategy(new NoInterest());
        acc2.applyInterest();
        System.out.println(acc2.getAccountType() + " balance after no interest strategy: " + acc2.getBalance());

        System.out.println("\n--- Final Account balances ---");
        for (Account a : accounts) {
            System.out.println(a.getAccountType() + " final balance: " + a.getBalance());
        }

        // =================== LOAN SYSTEM ===================
        System.out.println("\n===== LOAN SYSTEM DEMO =====\n");

        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + (365L * 24 * 60 * 60 * 1000));

        Loan loan1 = new PersonalLoanBuilder()
                .setLoanID(1)
                .setCustomerID(201)
                .setAmount(10000)
                .setInterestRate(5)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .build();

        Loan loan2 = new CarLoanBuilder()
                .setLoanID(2)
                .setCustomerID(202)
                .setAmount(20000)
                .setInterestRate(6)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .build();

        Loan loan3 = new MortgageLoanBuilder()
                .setLoanID(3)
                .setCustomerID(203)
                .setAmount(50000)
                .setInterestRate(4)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .build();

        Loan[] loans = { loan1, loan2, loan3 };

        LoanService loanService = new LoanService();
        for (Loan l : loans) loanService.addLoan(l);

        System.out.println("---- Initial Loan Data ----");
        printLoans(loans);

        System.out.println("\n---- Approving, Activating & Applying Interest ----");
        for (Loan l : loans) {
            loanService.approveLoan(l);
            loanService.activateLoan(l);
            loanService.applyInterest(l);
        }
        printLoans(loans);

        System.out.println("\n---- Paying Installments ----");
        loanService.payInstallment(loan1, 2000);
        loanService.payInstallment(loan2, 3000);
        printLoans(loans);

        // =================== CARD SYSTEM ===================
        System.out.println("\n===== CARD SYSTEM DEMO =====");

        CreditCard creditCard = new CreditCardBuilder()
                .setCardID(1)
                .setCustomerID(301)
                .setAccountID(acc1.getAccountID())
                .setCardNumber("4111-1111-1111-1111")
                .setProvider(CardProvider.VISA)
                .setCVV("123")
                .setExpiryDate(new Date(System.currentTimeMillis() + 31536000000L))
                .build();

        DebitCard debitCard = new DebitCardBuilder()
                .setCardID(2)
                .setCustomerID(302)
                .setAccountID(acc2.getAccountID())
                .setCardNumber("5222-2222-2222-2222")
                .setProvider(CardProvider.MASTERCARD)
                .setCVV("456")
                .setExpiryDate(new Date(System.currentTimeMillis() + 31536000000L))
                .build();

        creditCard.activate();
        debitCard.activate();

        creditCard.applyFee();
        debitCard.applyFee();

        creditCard.displayCardInfo();
        debitCard.displayCardInfo();

        System.out.println("\n===== END OF DEMO =====");
    }

    private static void printLoans(Loan[] loans) {
        for (Loan l : loans) {
            System.out.println(
                    "Loan Type: " + l.getClass().getSimpleName()
                            + " | Amount: " + l.getAmount()
                            + " | Status: " + l.getStatus()
            );
        }
    }
}
