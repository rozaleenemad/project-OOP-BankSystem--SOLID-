package builder.account;

import models.FixedDepositAccount;

public class FixedDepositAccountBuilder {
    private int accountID;
    private int customerID;
    private String currency;
    private double balance;
    private double interestRate;
    private int durationMonths;

    public FixedDepositAccountBuilder setAccountID(int id) { 
        this.accountID = id; return this; 
    }
    public FixedDepositAccountBuilder setCustomerID(int id) { 
        this.customerID = id; return this; 
    }
    public FixedDepositAccountBuilder setCurrency(String c) { 
        this.currency = c; return this; 
    }
    public FixedDepositAccountBuilder setBalance(double b) { 
        this.balance = b; return this; 
    }
    public FixedDepositAccountBuilder setInterestRate(double r) { 
        this.interestRate = r; return this; 
    }
    public FixedDepositAccountBuilder setDurationMonths(int months) { 
        this.durationMonths = months; return this; 
    }

    public FixedDepositAccount build() {
        return new FixedDepositAccount(accountID, customerID, currency, balance, interestRate, durationMonths);
    }
    // ترجع object من نوع FixedDepositAccount بعد ما تبنيه بالمعطيات اللي حددناها
}
