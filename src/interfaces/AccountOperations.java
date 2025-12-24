package interfaces;

import models.Account;

public interface AccountOperations {
    boolean deposit(double amount);
    boolean withdraw(double amount);
    boolean transfer(double amount, Account to);

}