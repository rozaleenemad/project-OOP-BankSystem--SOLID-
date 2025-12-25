# SOLID Principles – Overall System Documentation
---

##  Single Responsibility Principle (SRP)

**المبدأ:**
كل كلاس يكون مسؤول عن وظيفة واحدة فقط.

### التطبيق في النظام:

### Models

* `Account` → يحتفظ ببيانات الحساب وينفذ العمليات الأساسية فقط
* `Loan` → يحتفظ ببيانات القرض وسلوكه (repayment, status)
* `Card` → يحتفظ ببيانات الكارت وسلوكه (activate, block)

### Services

* `AccountService` → منطق الأعمال الخاص بالحسابات فقط
* `LoanService` → إدارة القروض (approve, repay)
* `CardService` → إدارة الكروت (issue, block, fees)

### Builders

* Builders مسؤولة فقط عن **إنشاء الكائنات**
* لا تحتوي على Business Logic

### Strategies

* كل Strategy مسؤولة عن **حساب واحد فقط** (Interest / Fee)

✔ النتيجة: تغيير منطق معين لا يؤثر على باقي النظام.

---

##  Open / Closed Principle (OCP)

**المبدأ:**
الكلاسات مفتوحة للإضافة، مغلقة للتعديل.

### التطبيق في النظام:

### Accounts

* إضافة نوع حساب جديد → إنشاء Subclass جديد فقط
* لا يتم تعديل `Account`

### Loans

* إضافة طريقة حساب فائدة جديدة → Strategy جديدة
* بدون تعديل `Loan`

### Cards

* إضافة نوع كارت جديد → Subclass + Strategy جديدة

### Builders

* كل نوع جديد له Builder مستقل

✔ النتيجة: النظام قابل للتوسع بدون كسر الكود القديم.

---

##  Liskov Substitution Principle (LSP)

**المبدأ:**
أي Subclass يجب أن يمكن استخدامها مكان الـ Parent بدون كسر السلوك.

### التطبيق في النظام:

### Account

* `CurrentAccount` يمكن استخدامه مكان `Account`
* `withdraw()` يحترم نفس العقد (true / false)

### Loan

* أي نوع Loan يمكن التعامل معه كـ `Loan`

### Card

* `CreditCard` و `DebitCard` يمكن استخدامهم كـ `Card`

✔ النتيجة: Polymorphism حقيقي بدون Bugs.

---

##  Interface Segregation Principle (ISP)

**المبدأ:**
لا يُجبر كلاس على تنفيذ Methods لا يحتاجها.

### التطبيق في النظام:

### Interfaces

* `AccountOperations` → عمليات الحساب فقط
* `LoanOperations` → عمليات القرض فقط
* `CardOperations` → عمليات الكارت فقط

### Strategies

* `InterestStrategy` → حساب الفائدة فقط
* `FeeStrategy` → حساب الرسوم فقط

✔ النتيجة: Interfaces صغيرة وواضحة وسهلة الصيانة.

---

##  Dependency Inversion Principle (DIP)

**المبدأ:**
الـ High-Level Modules لا تعتمد على Low-Level Modules، بل على Abstractions.

### التطبيق في النظام:

### Strategies

* `Account` يعتمد على `InterestStrategy` interface
* `Loan` يعتمد على `LoanInterestStrategy`
* `Card` يعتمد على `CardFeeStrategy`

### Services

* Services تتعامل مع `Account / Loan / Card` وليس مع Subclasses مباشرة

✔ النتيجة: تغيير أي Implementation بدون التأثير على النظام.

---

##  Design Patterns Supporting SOLID

### Strategy Pattern

* فصل منطق الحساب (Interest / Fee)
* دعم OCP + DIP

### Builder Pattern

* فصل إنشاء الكائنات عن استخدامها
* دعم SRP + OCP

### Service Layer Pattern

* فصل Business Logic عن Models
* دعم SRP

---

##  Summary Table

| Principle | Applied Through                              |
| --------- | -------------------------------------------- |
| SRP       | Models / Services / Builders / Strategies    |
| OCP       | Subclasses / Strategies / Builders           |
| LSP       | Polymorphism بين Parent و Subclasses         |
| ISP       | Small focused interfaces                     |
| DIP       | Dependency on interfaces not implementations |

---
