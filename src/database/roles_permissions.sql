USE banksystem;

-- Roles
CREATE ROLE Manager;
CREATE ROLE Teller;

-- Permissions
GRANT SELECT, INSERT, UPDATE, DELETE ON Customers TO Manager;
GRANT SELECT, INSERT, UPDATE ON Accounts TO Manager;

GRANT SELECT, INSERT, UPDATE ON Accounts TO Teller;
GRANT SELECT ON Customers TO Teller;

-- Users
CREATE USER 'manager1'@'localhost' IDENTIFIED BY 'pass123';
GRANT Manager TO 'manager1';

CREATE USER 'teller1'@'localhost' IDENTIFIED BY 'pass123';
GRANT Teller TO 'teller1';
