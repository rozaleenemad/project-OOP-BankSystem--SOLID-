USE BankSystem;

-- ===================================
-- Customers
-- ===================================
INSERT INTO Customers (FirstName, LastName, NationalID, Phone, Email, Address, BirthDate)
VALUES
('Ahmed', 'Ali', 'ID10001', '01012345678', 'ahmed.ali@example.com', '10 Nile St, Cairo', '1990-05-12'),
('Sara', 'Hassan', 'ID10002', '01023456789', 'sara.hassan@example.com', '22 Tahrir St, Alexandria', '1985-11-20'),
('Mohamed', 'Youssef', 'ID10003', '01034567890', 'mohamed.youssef@example.com', '5 El-Maadi St, Cairo', '1992-07-08'),
('Laila', 'Mostafa', 'ID10004', '01045678901', 'laila.mostafa@example.com', '12 Heliopolis St, Cairo', '1995-02-15'),
('Omar', 'Said', 'ID10005', '01056789012', 'omar.said@example.com', '18 Nasr City St, Cairo', '1988-09-30'),
('Mona', 'Ibrahim', 'ID10006', '01067890123', 'mona.ibrahim@example.com', '8 Maadi St, Cairo', '1993-04-25'),
('Karim', 'Fahmy', 'ID10007', '01078901234', 'karim.fahmy@example.com', '33 Garden City St, Cairo', '1987-08-14'),
('Nour', 'Samir', 'ID10008', '01089012345', 'nour.samir@example.com', '7 Shoubra St, Cairo', '1996-12-05'),
('Youssef', 'Khaled', 'ID10009', '01090123456', 'youssef.khaled@example.com', '21 Dokki St, Giza', '1991-03-18'),
('Dina', 'Mahmoud', 'ID10010', '01001234567', 'dina.mahmoud@example.com', '14 Mohandessin St, Giza', '1989-06-22');

-- ===================================
-- Branches
-- ===================================
INSERT INTO Branches (BranchName, City, Address, ManagerID)
VALUES
('Cairo Central', 'Cairo', '10 Nile St', NULL),
('Alexandria Main', 'Alexandria', '22 Tahrir St', NULL),
('Giza West', 'Giza', '14 Mohandessin St', NULL);

-- ===================================
-- Employees
-- ===================================
INSERT INTO Employees (FirstName, LastName, Email, Phone, Position, Salary, BranchID)
VALUES
('Mostafa', 'Ziad', 'mostafa.ziad@bank.com', '01011112222', 'Branch Manager', 15000.00, 1),
('Meme', 'Emad', 'meme.emad@bank.com', '01022223333', 'Teller', 8000.00, 1),
('Karim', 'Saes', 'karim.saes@bank.com', '01033334444', 'Accountant', 9000.00, 1),
('Mariam', 'Hassan', 'mariam.hassan@bank.com', '01044445555', 'Branch Manager', 15500.00, 2),
('Samia', 'Nabil', 'samia.nabil@bank.com', '01055556666', 'Teller', 8200.00, 2),
('Ezzat', 'Ibrahim', 'ezzat.ibrahim@bank.com', '01066667777', 'Accountant', 8800.00, 2),
('Abdalla', 'Anowr', 'abdalla.anowr@bank.com', '01077778888', 'Branch Manager', 16000.00, 3),
('Khaled', 'Samir', 'khaled.samir@bank.com', '01088889999', 'Teller', 8100.00, 3),
('Samir', 'Youssef', 'samir.youssef@bank.com', '01099990000', 'Accountant', 8900.00, 3);

-- ===================================
-- Update Branch ManagerID
-- ===================================
UPDATE Branches SET ManagerID = 1 WHERE BranchID = 1;
UPDATE Branches SET ManagerID = 4 WHERE BranchID = 2;
UPDATE Branches SET ManagerID = 7 WHERE BranchID = 3;

-- ===================================
-- Accounts
-- ===================================
INSERT INTO Accounts (CustomerID, AccountType, Currency, Balance, OpenDate, Status, InterestRate, OverdraftLimit)
VALUES
(1, 'Saving', 'EGP', 15000.50, '2025-01-16', 'Active', 5.5, 0),
(1, 'Current', 'EGP', 5000.00, '2025-02-01', 'Active', 0, 2000),
(2, 'Saving', 'EGP', 12000.00, '2025-01-21', 'Active', 4.5, 0),
(3, 'Current', 'EGP', 8000.00, '2025-02-05', 'Active', 0, 3000),
(4, 'Saving', 'EGP', 25000.00, '2025-02-02', 'Active', 6.0, 0),
(5, 'Current', 'EGP', 10000.00, '2025-02-10', 'Active', 0, 4000);

-- ===================================
-- Cards
-- ===================================
INSERT INTO Cards (CustomerID, AccountID, CardNumber, CardType, Provider, ExpiryDate, CVV, IssueDate)
VALUES
(1, 1, '4000123412341234', 'Debit', 'Visa', '2028-01-20', '123', '2025-01-20'),
(2, 3, '4000987698769876', 'Debit', 'MasterCard', '2028-02-21', '345', '2025-02-21'),
(5, 6, '4000987698769877', 'Credit', 'Visa', '2028-02-21', '678', '2025-02-21');

-- ===================================
-- Loans
-- ===================================
INSERT INTO Loans (CustomerID, LoanType, Amount, InterestRate, StartDate, EndDate, Status)
VALUES
(2, 'Personal', 50000.00, 10.5, '2025-01-01', '2026-01-01', 'Active'),
(5, 'Car', 150000.00, 8.5, '2025-02-15', '2028-02-15', 'Active'),
(6, 'Mortgage', 1000000.00, 7.2, '2025-03-01', '2035-03-01', 'Active');

-- ===================================
-- Transactions
-- ===================================
INSERT INTO Transactions (FromAccount, ToAccount, Amount, Type, Status)
VALUES
(1, 3, 2000.00, 'Transfer', 'Success'),
(3, 1, 500.00, 'Transfer', 'Success'),
(1, NULL, 1000.00, 'Withdraw', 'Success'),
(NULL, 2, 1500.00, 'Deposit', 'Success'),
(2, 6, 3000.00, 'LoanPayment', 'Pending');



UPDATE Accounts
 SET Balance = Balance - 2000.00
  WHERE AccountID = 1;