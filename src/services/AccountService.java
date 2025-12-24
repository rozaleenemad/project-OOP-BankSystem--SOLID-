package services;

import java.util.ArrayList;
import java.util.List;
import models.Account;

public class AccountService {

    private final List<Account> accounts = new ArrayList<>();
    //أي حساب جديد هيتسجل هنا عشان النظام يعرف عنه و final هو ثابت مش هيتغير لكن ممكن اضيف او امسح حساب

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAllAccounts() {
        return accounts;
    }
    //ترجع قائمة بكل الحسابات اللي موجودة في النظام.

    public void deposit(Account account, double amount) {
        account.deposit(amount);
    }

    public void transfer(Account from, Account to, double amount) {
        from.transfer(amount, to);
    }

    public void applyInterest(Account account) {
        account.applyInterest();
    }
    // الدوال اللي اي اكونت ينفذها 
}
