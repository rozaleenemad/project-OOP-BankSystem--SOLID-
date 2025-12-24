package builder.account;

import models.SavingAccount;

public class SavingAccountBuilder {
    private int accountID;
    private int customerID;
    private String currency;
    private double balance;
    private double interestRate;
// ------setter for parameter----
    public SavingAccountBuilder setAccountID(int id) { 
        this.accountID = id; return this; 
    }
    public SavingAccountBuilder setCustomerID(int id) { 
        this.customerID = id; return this; 
    }
    public SavingAccountBuilder setCurrency(String c) { 
        this.currency = c; return this; 
    }
    public SavingAccountBuilder setBalance(double b) { 
        this.balance = b; return this; 
    }
    public SavingAccountBuilder setInterestRate(double r) { 
        this.interestRate = r; return this; 
    }

    public SavingAccount build() {
        return new SavingAccount(accountID, customerID, currency, balance, interestRate);
    }
        // ترجع object من نوع SavingAccount بعد ما تبنيه بالمعطيات اللي حددناها

}
