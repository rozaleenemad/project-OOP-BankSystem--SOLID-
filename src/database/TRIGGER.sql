USE banksystem;

DELIMITER 

-- =====================================
--  Interest for Saving Accounts
-- =====================================
CREATE TRIGGER SavingInterestAfterUpdate
AFTER UPDATE ON Accounts
FOR EACH ROW
BEGIN

-- لو الحساب من نوع Saving، يحسب الفائدة ويضيفها مباشرة للرصيد.
    -- بعد تعديل اي حساب يتنفذ  التريجر
    IF NEW.AccountType = 'Saving' THEN
        SET interest = NEW.Balance * NEW.InterestRate / 100;
        SET NEW.Balance = NEW.Balance + interest;

    END IF;
END;

-- =====================================
--  Prevent overdraft beyond limit
-- =====================================
CREATE TRIGGER PreventOverdraft
BEFORE UPDATE ON Accounts
FOR EACH ROW
BEGIN
-- يمنع الرصيد من أن يصبح أقل من حد السحب على المكشوف، ويرجع خطأ إذا حدث ذلك.    -- 
-- يتنفذ قبل تعديل اي حساب
    IF NEW.Balance < -NEW.OverdraftLimit THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Balance exceeds overdraft limit!';
    END IF;
END;

-- =====================================
--  Update Account Balance after Transaction
-- =====================================
CREATE TRIGGER UpdateBalanceAfterTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
-- يضيف المبلغ للمرسل إليه ويخصم نفس المبلغ من المرسل.    -- بعد اضافه صف جديد ف ال transaction 
    UPDATE Accounts
    SET Balance = Balance + NEW.Amount
    WHERE AccountID = NEW.ToAccount;

    -- خصم رصيد المرسل
    UPDATE Accounts
    SET Balance = Balance - NEW.Amount
    WHERE AccountID = NEW.FromAccount;
END;

-- =====================================
-- 4️⃣ Prevent Transaction if Insufficient Funds
-- =====================================
CREATE TRIGGER PreventInsufficientFunds
BEFORE INSERT ON Transactions
FOR EACH ROW
BEGIN
-- يمنع إضافة Transaction لو رصيد المرسل أقل من المبلغ المطلوب، ويرجع خطأ.


-- بل إضافة صف جديد في Transactions

    DECLARE current_balance DOUBLE;

    SELECT Balance INTO current_balance 
    FROM Accounts 
    WHERE AccountID = NEW.FromAccount;

    IF current_balance < NEW.Amount THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Insufficient funds for transaction!';
    END IF;
END;

-- =====================================
-- 5️⃣ Log Loan Status Changes
-- =====================================
CREATE TRIGGER LogLoanChanges
AFTER UPDATE ON Loans
FOR EACH ROW
BEGIN
-- يسجل كل تغيير في حالة القرض في جدول LoanAudit مع التاريخ والوقت، مفيد للتتبع والمراجعة.
-- بعد تعديل أي قرض في Loans
    INSERT INTO LoanAudit (LoanID, OldStatus, NewStatus, ChangedAt)
    VALUES (OLD.LoanID, OLD.Status, NEW.Status, NOW());
END;

DELIMITER ;
