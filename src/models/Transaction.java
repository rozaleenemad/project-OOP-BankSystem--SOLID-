package models;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    private int transactionID;
    private int fromAccount;
    private int toAccount;
    private BigDecimal amount;
    private String type; 
    private String status; 
    private Date createdAt;

    public Transaction(int transactionID, int fromAccount, int toAccount, BigDecimal amount,
                       String type, String status, Date createdAt) {
        this.transactionID = transactionID;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Transaction() {}

    public int getTransactionID() { return transactionID; }
    public void setTransactionID(int transactionID) { this.transactionID = transactionID; }

    public int getFromAccount() { return fromAccount; }
    public void setFromAccount(int fromAccount) { this.fromAccount = fromAccount; }

    public int getToAccount() { return toAccount; }
    public void setToAccount(int toAccount) { this.toAccount = toAccount; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public void displayTransaction() {
        System.out.println("Transaction: " + type + ", Amount: " + amount + ", Status: " + status);
    }
}
