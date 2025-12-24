USE banksystem;

-- ===========================
-- Views
-- ===========================

-- AccountDetails View
DROP VIEW IF EXISTS AccountDetails;
GO
CREATE VIEW AccountDetails AS
SELECT 
    a.AccountID,         -- Account attribute
    a.AccountType,       -- Account attribute
    a.Balance,           -- Account attribute
    a.Currency,          -- Account attribute
    c.FirstName,         -- Customer attribute
    c.LastName,          -- Customer attribute
    c.NationalID         -- Customer attribute
FROM Accounts a
JOIN Customers c ON a.CustomerID = c.CustomerID;
GO

-- LoanDetails View
DROP VIEW IF EXISTS LoanDetails;
GO
CREATE VIEW LoanDetails AS
SELECT 
    l.LoanID,            -- Loan attribute
    l.LoanType,          -- Loan attribute
    l.Amount,            -- Loan attribute
    l.Status,            -- Loan attribute
    c.FirstName,         -- Customer attribute
    c.LastName           -- Customer attribute
FROM Loans l
JOIN Customers c ON l.CustomerID = c.CustomerID;
GO

-- EmployeeDetails View
DROP VIEW IF EXISTS EmployeeDetails;
GO
CREATE VIEW EmployeeDetails AS
SELECT 
    e.EmployeeID,        -- Employee attribute
    e.FirstName,         -- Employee attribute
    e.LastName,          -- Employee attribute
    e.Position,          -- Employee attribute
    b.BranchName         -- Branch attribute
FROM Employees e
LEFT JOIN Branches b ON e.BranchID = b.BranchID;
GO

-- ===========================
-- Example SELECT Queries
-- ===========================

-- Active accounts
SELECT * 
FROM Accounts 
WHERE Status='Active';

-- Total balance per customer
SELECT 
    CustomerID, 
    SUM(Balance) AS TotalBalance
FROM Accounts
GROUP BY CustomerID;

-- Employees ordered by salary descending
SELECT * 
FROM Employees 
ORDER BY Salary DESC;
