USE banksystem;

BEGIN TRANSACTION;

--  خصم المبلغ من الحساب المرسل

UPDATE Accounts
SET Balance = Balance - 500
WHERE AccountID = 1;
-- إضافة المبلغ للحساب المستفيد

UPDATE Accounts
SET Balance = Balance + 500
WHERE AccountID = 2;
-- لو كل حاجة تمام، نحفظ التغييرات

COMMIT;


-- لو حصل أي مشكلة بدل الـ COMMIT نعمل ROLLBACK
--ROLLBACK;