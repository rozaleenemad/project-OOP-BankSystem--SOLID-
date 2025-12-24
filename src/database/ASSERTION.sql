USE banksystem;

-- Ensure account balance never goes below negative overdraft (if OverdraftLimit is a column on Accounts)
ALTER TABLE Accounts
ADD CONSTRAINT CHK_Accounts_Balance_Overdraft
CHECK (Balance >= -OverdraftLimit);

-- Ensure loan amount is non-negative
ALTER TABLE Loans
ADD CONSTRAINT CHK_Loans_Amount_NonNegative
CHECK (Amount >= 0);