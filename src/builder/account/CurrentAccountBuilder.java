package builder.account;

import models.CurrentAccount;

public class CurrentAccountBuilder {
    private int accountID;
    private int customerID;
    private String currency;
    private double balance;
    private double overdraftLimit;
// -------setter for prameter-----
    public CurrentAccountBuilder setAccountID(int id) { 
        this.accountID = id; return this; 
    }
    public CurrentAccountBuilder setCustomerID(int id) { 
        this.customerID = id; return this; 
    }
    public CurrentAccountBuilder setCurrency(String c) { 
        this.currency = c; return this; 
    }
    public CurrentAccountBuilder setBalance(double b) { 
        this.balance = b; return this; 
    }
    public CurrentAccountBuilder setOverdraftLimit(double limit) { 
        this.overdraftLimit = limit; return this; 
    }

    public CurrentAccount build() {
        return new CurrentAccount(accountID, customerID, currency, balance, overdraftLimit);
    }
    // ترجع object من نوع CurrentAccount بعد ما تبنيه بالمعطيات اللي حددناها
}
