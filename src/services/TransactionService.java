package services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import models.Transaction;

public class TransactionService {

    private final List<Transaction> transactions = new ArrayList<>();

    // إضافة معاملة
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    // الحصول على كل المعاملات
    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    // إنشاء معاملة جديدة
    public void createTransaction(int fromAccount, int toAccount, BigDecimal amount, String type) {
        Transaction t = new Transaction();
        t.setFromAccount(fromAccount);
        t.setToAccount(toAccount);
        t.setAmount(amount);
        t.setType(type);
        t.setStatus("SUCCESS");
        transactions.add(t);
        System.out.println("Transaction created: " + fromAccount + " -> " + toAccount + " | Amount: " + amount);
    }

    // طباعة كل المعاملات
    public void printTransactions() {
        for (Transaction t : transactions) {
            System.out.println(
                    "From: " + t.getFromAccount() +
                    " | To: " + t.getToAccount() +
                    " | Amount: " + t.getAmount() +
                    " | Type: " + t.getType() +
                    " | Status: " + t.getStatus()
            );
        }
    }
}
