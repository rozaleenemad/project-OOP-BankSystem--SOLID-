


 SERVICES.md

# AccountService Documentation

## Overview
AccountService acts as a service layer managing account operations.
Separates business logic from Account models.

---

### Attributes
- accounts: List<Account>
  - Stores all accounts in system
  - final ensures reference doesn't change
  - Can add/remove accounts

---

### Methods

#### addAccount(Account account)
- Registers a new account

#### getAllAccounts()
- Returns all accounts in the system

#### deposit(Account account, double amount)
- Delegates deposit to Account object

#### transfer(Account from, Account to, double amount)
- Delegates transfer to Account object

#### applyInterest(Account account)
- Applies interest using configured strategy

---

### Design Principles
- SRP: Service handles system-wide logic
- Separation of Concerns: Account logic remains in Account
- Layered Architecture: UI/Controller interacts with Service

=================================
=================================

---
 (Loan Module)
# SOLID Principles Applied to Loan Module

## 1. Single Responsibility Principle (SRP)
- `Loan` → stores loan data and base operations
- `LoanService` → handles business logic (approval, payment, activation)
- Builders → handle object creation

## 2. Open/Closed Principle (OCP)
- `Loan` is abstract and open for extension:
  - CarLoan, PersonalLoan, MortgageLoan
- Base class remains unchanged when adding new loan types

## 3. Liskov Substitution Principle (LSP)
- Any subclass can replace Loan without breaking behavior
- Example: `payInstallment()` works for any loan type

## 4. Interface Segregation Principle (ISP)
- `LoanOperations` contains only relevant methods
- No unnecessary methods for loan subclasses

## 5. Dependency Inversion Principle (DIP)
- `Loan` depends on `LoanInterestStrategy` interface, not concrete classes
- Allows easy swapping of interest calculation strategies
