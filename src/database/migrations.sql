-- ===================================
-- DROP & CREATE DATABASE
-- ===================================
DROP DATABASE IF EXISTS BankSystem;
CREATE DATABASE BankSystem;
USE BankSystem;

-- ===================================
-- TABLE: Customers
-- ===================================
CREATE TABLE Customers (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR('100'),
    LastName VARCHAR(100),
    full_name VARCHAR(255) AS (CONCAT(FirstName, ' ', LastName)) STORED,
    NationalID VARCHAR(20) UNIQUE,
    Phone VARCHAR(20),
    Email VARCHAR(120) UNIQUE,
    Address VARCHAR(255),
    BirthDate DATE,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ===================================
-- TABLE: Branches
-- ===================================
CREATE TABLE Branches (
    BranchID INT AUTO_INCREMENT PRIMARY KEY,
    BranchName VARCHAR(100),
    City VARCHAR(100),
    Address VARCHAR(255),
    ManagerID INT
);

-- ===================================
-- TABLE: Employees
-- ===================================
CREATE TABLE Employees (
    EmployeeID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(100),
    LastName VARCHAR(100),
    Email VARCHAR(100) UNIQUE,
    Phone VARCHAR(20),
    Position VARCHAR(50),
    Salary DECIMAL(10,2),
    BranchID INT,
    FOREIGN KEY (BranchID) REFERENCES Branches(BranchID)
);

-- ===================================
-- FOREIGN KEY: Branch Manager
-- ===================================
ALTER TABLE Branches 
ADD CONSTRAINT fk_manager
FOREIGN KEY (ManagerID) REFERENCES Employees(EmployeeID);

-- ===================================
-- TABLE: Accounts
-- ===================================
CREATE TABLE Accounts (
    AccountID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerID INT NOT NULL,
    AccountType ENUM('Saving','Current','Fixed'),
    Currency ENUM('EGP','USD','EUR'),
    Balance DECIMAL(15,2),
    OpenDate DATE,
    Status ENUM('Active','Closed','Frozen'),
    InterestRate DECIMAL(5,2),
    OverdraftLimit DECIMAL(10,2),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

-- ===================================
-- TABLE: Cards
-- ===================================
CREATE TABLE Cards (
    CardID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerID INT NOT NULL,
    AccountID INT NOT NULL,
    CardNumber VARCHAR(20) UNIQUE NOT NULL,
    CardType ENUM('Debit','Credit','Prepaid') NOT NULL,
    Provider ENUM('Visa','MasterCard','AmericanExpress') NOT NULL,
    ExpiryDate DATE NOT NULL,
    CVV VARCHAR(4) NOT NULL,
    Status ENUM('Active','Blocked','Expired') DEFAULT 'Active',
    IssueDate DATE NOT NULL,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

-- ===================================
-- TABLE: Loans
-- ===================================
CREATE TABLE Loans (
    LoanID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerID INT,
    LoanType ENUM('Personal','Car','Mortgage'),
    Amount DECIMAL(15,2),
    InterestRate DECIMAL(5,2),
    StartDate DATE,
    EndDate DATE,
    Status ENUM('Pending','Approved','Rejected','Closed'),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

-- ===================================
-- TABLE: Transactions
-- ===================================
CREATE TABLE Transactions (
    TransactionID INT AUTO_INCREMENT PRIMARY KEY,
    FromAccount INT,
    ToAccount INT,
    Amount DECIMAL(15,2),
    Type ENUM('Transfer','Deposit','Withdraw','LoanPayment'),
    Status ENUM('Success','Failed','Pending'),
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (FromAccount) REFERENCES Accounts(AccountID),
    FOREIGN KEY (ToAccount) REFERENCES Accounts(AccountID)
);



