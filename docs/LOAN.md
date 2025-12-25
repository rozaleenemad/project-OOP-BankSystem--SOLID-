# Loan Module Documentation 


##  LoanOperations Interface

### ليه عملنا Interface؟

* علشان **أي نوع Loan** يبقى ليه نفس السلوك الأساسي.
* نحقق **Interface Segregation Principle (ISP)**.
-> أي قرض يجب أن يعرف العمليات الخاصة به.

### Methods:

* `payInstallment(double amount)`

  * مسؤول عن دفع القسط.
  * بيرجع `boolean` علشان نعرف العملية نجحت ولا لأ.

* `approveLoan()`

  * يغيّر حالة القرض إلى APPROVED.

* `rejectLoan()`

  * يرفض القرض نهائيًا.

* `activate()`

  * يفعّل القرض بعد الموافقة.

➡️ أي Class implements الواجهة دي **ملزم** ينفذ العمليات دي.

---

## LoanStatus Enum

* يمنع الأخطاء المنطقية في النظام.
* يخلي كل حالة واضحة وصريحة.

### الحالات:

* `PENDING` → لسه متقدّم عليه
* `APPROVED` → البنك وافق
* `ACTIVE` → القرض شغال وبيتدفع
* `PAID` → القرض اتسدد بالكامل
* `REJECTED` → مرفوض

➡️ كل Method في Loan بتعمل **Validation** بناءً على الحالة.

---

##  Abstract Class: Loan

### ليه abstract؟
* يسمح لأي نوع قرض أن يتعامل على أنه Loan.
* مفيش حاجة اسمها Loan عام.
* لازم يكون Car / Personal / Mortgage.
* يحتوي على attributes مشتركة لكل القروض
* يحتوي على methods عامة متاحة لكل subclass:

### Attributes (مشتركة):

* `loanID` → رقم القرض
* `customerID` → صاحب القرض
* `amount` → المبلغ المتبقي
* `interestRate` → نسبة الفائدة
* `startDate / endDate` → مدة القرض
* `status` → حالة القرض
* `interestStrategy` → استراتيجية حساب الفائدة

---

- Protected LoanInterestStrategy interestStrategy
->  كل قرض قد يكون له طريقة حساب فائدة مختلفة.
### Constructor

* يستقبل البيانات الأساسية.
* يضبط الحالة الافتراضية:
* `status = PENDING`

➡️ ده يحقق **SRP**: الكلاس مسؤول عن بياناته فقط.

---
## Interest Strategy Methods

### applyInterest()
  * `interestStrategy.calculateInterest(this)`
* يزود الفائدة على المبلغ.
* ليحسب الفائدة حسب نوع القرض
مثال:
 CarLoan → this.interestStrategy = new CarLoanInterest();

➡️ **DIP + OCP**

* Loan لا يعرف نوع الفائدة.
* يعتمد على interface فقط.
* يمكن تغيير طريقة حساب الفائدة لكل قرض دون تعديل الكلاس الأساسي Loan.

---

### payInstallment(double value)

#### Validation:

* القرض لازم يكون `ACTIVE`
* القيمة > 0
* القيمة ≤ المبلغ المتبقي

#### Logic:

* يخصم القسط من `amount`
* لو المبلغ بقى 0:

  * الحالة تتحول `PAID`

➡️ يحافظ على **Business Rules**.

---

### approveLoan()

* يغير الحالة إلى `APPROVED`

### rejectLoan()

* يغير الحالة إلى `REJECTED`

### activate()

* **شرط مهم:**

  * مينفعش يتفعل إلا لو Approved

➡️ ده يمنع أخطاء تشغيلية.

---

##  Subclasses (أنواع القروض)
كل subclass يمثل نوعًا معينًا من القروض

- Overriding Methods:
 يمكن تعديل وظائف معينة لو احتاج النوع لتصرف مختلف (حسب الحاجة).

### CarLoan

* بيرث كل شيء من Loan.
* يحدد:

  * `interestStrategy = new CarLoanInterest()`

➡️ فائدة أعلى (×1.5)

---

### PersonalLoan

* يستخدم:

  * `PersonalLoanInterest`

➡️ فائدة عادية.

---

### MortgageLoan

* يستخدم:

  * `MortgageLoanInterest`

➡️ فائدة أقل (×0.9)

---

##  Strategy Pattern (Interest)

### LoanInterestStrategy Interface

* Method واحد:

  * `calculateInterest(Loan loan)`
كل استراتيجية تحدد كيفية حساب الفائدة.
➡️ يسمح بتغيير الحساب بدون لمس Loan.


---

### Implementations

* `CarLoanInterest`
* `PersonalLoanInterest`
* `MortgageLoanInterest`

كل واحدة:

* تستخدم `loan.getAmount()`
* تستخدم `loan.getInterestRate()`
* تضرب بمعامل مختلف

➡️ **OCP** 100٪.

---

##  Builder Pattern (Loan Builders)

### المشكلة اللي حلها

* تسهيل إنشاء Loan objects بدون الحاجة لكتابة constructor طويل.

* Builder لكل نوع قرض:

  * CarLoanBuilder
  * PersonalLoanBuilder
  * MortgageLoanBuilder

كل attribute له setter method:
* كل setter بيرجع `this`
* نستخدم chaining
* `build()`:
 ينشئ object من النوع المناسب
```java 
 CarLoan loan = new CarLoanBuilder() 
 .setLoanID(1) .
 setCustomerID(100) .
 setAmount(50000) .
 setInterestRate(10) .
 setStartDate(new Date()) .
 setEndDate(new Date(System.currentTimeMillis()+365*24*60*60*1000)) .
 build()
 ```

➡️ Creation Logic منفصل عن Business Logic.
➡️  **(SOLID / OCP)**:
 يمكن إضافة نوع قرض جديد بسهولة بدون تعديل Builders أو Service قديم.
---

##  LoanService
* طبقة Business Logic.
* يتعامل مع Loan كـ abstract.
* تدير القروض وتنفيذ العمليات على النظام.

### Attributes

* `private final List List<Loan> loans`
 → قائمة بكل القروض في النظام.
---

### Methods

* `addLoan(Loan loan)` 
→ تضيف قرض جديد للنظام.
* `getAllLoans()`
→ ترجع جميع القروض.
* `approveLoan(Loan loan)`
→ ينفذ loan.approveLoan().
* `rejectLoan(Loan loan)`
→ ينفذ loan.rejectLoan().
* `activateLoan(Loan loan)`
→ ينفذ loan.activate().
* `payInstallment(Loan loan, double amount)`
→ ينفذ القسط باستخدام loan.payInstallment(amount).
* `applyInterest(Loan loan)`
 → ينفذ loan.applyInterest() باستخدام الاستراتيجية المناسبة لكل نوع قرض.


**SRP** → كل كلاس له مسؤولية واحدة
**DIP** → Service تعتمد على الـ Loan abstract/interface وليس على أي subclass مباشرة.
**OCP** → يمكن إضافة أنواع جديدة من القروض أو طرق جديدة لحساب الفائدة دون تعديل LoanService.


---

##  SOLID Summary 

### SRP

* Loan → بيانات + عمليات أساسية
* Service → Business Logic
* Builder → إنشاء Objects

### OCP

* إضافة Loan جديد بدون تعديل القديم

### LSP

* أي Subclass ينفع مكان Loan

### ISP

* Interface صغيرة ومركزة

### DIP

* Loan يعتمد على Strategy Interface

---


