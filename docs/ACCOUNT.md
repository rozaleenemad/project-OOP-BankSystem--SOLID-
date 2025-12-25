# Account Module Documentation 


## Account Interface

### AccountOperations

```java
boolean deposit(double amount);
boolean withdraw(double amount);
boolean transfer(double amount, Account to);
```

**Purpose:**

* يحدد العمليات الأساسية التي يجب أن يوفرها أي نوع حساب داخل النظام البنكي.
* يسمح بالتعامل مع أي حساب على أنه Account بدون معرفة نوعه الحقيقي (Polymorphism).
* كل method ترجع `true` في حالة نجاح العملية و `false` في حالة الفشل.

**SOLID – ISP:**

* كل حساب يعرف فقط العمليات التي تخصه.
* لا توجد methods غير ضرورية مفروضة على أي حساب.

---

##  Enums

### AccountStatus

```java
ACTIVE, CLOSED, FROZEN


* تحديد حالات الحساب المسموح بها فقط.
* منع تنفيذ أي عملية على حساب مغلق أو مجمد.

**Checked inside:**

* deposit
* withdraw
* transfer

---

##  Abstract Class: Account

### Why Abstract?
* يسمح لأي نوع حساب أن يتعامل على أنه Account.
* لا يمكن إنشاء حساب بدون نوع محدد.
* يمنع إنشاء object ناقص أو غير صالح.
* الإنشاء يكون فقط من خلال subclasses.
* يحتوي على attributes مشتركة لكل الحسابات
* يحتوي على methods عامة متاحة لكل subclass 
deposit(), withdraw(), transfer().

---

### Attributes

| Attribute        | Type             | Purpose               |
| ---------------- | ---------------- | ------------------    |
| accountID        | int              | رقم الحساب           |
| customerID       | int              | صاحب الحساب          |
| accountType      | String           | نوع الحساب           |
| currency         | String           | العملة               |
| balance          | double           | الرصيد الحالي        |
| openDate         | Date             | تاريخ فتح الحساب     |
| status           | AccountStatus    | حالة الحساب          |
| interestStrategy | InterestStrategy | طريقة حساب الفائدة   |

---

### Protected InterestStrategy interestStrategy
 كل حساب قد يكون له طريقة حساب فايدة مختلفة

### Constructors

* Default constructor: مطلوب للـ Builders 
* Full constructor: يهيئ جميع الخصائص المشتركة.

---

### Getters & Setters

* تحقق مبدأ Encapsulation.
* تسمح بإضافة validation مستقبلًا.

---

##  Core Operations

### deposit(double amount)

```java
if (amount <= 0) return false;
if (status != AccountStatus.ACTIVE) return false;
balance += amount;
return true;
```

**Explanation:**

* يمنع الإيداع بمبلغ سالب أو صفر.
* يمنع الإيداع إذا الحساب غير ACTIVE.
* في حالة النجاح يتم تحديث الرصيد.

---

### withdraw(double amount)

```java
if (amount <= 0) return false;
if (status != AccountStatus.ACTIVE) return false;
if (balance >= amount) {
    balance -= amount;
    return true;
}
return false;
```

**Explanation:**

* يتحقق من صحة المبلغ.
* يتحقق من حالة الحساب.
* يمنع السحب إذا الرصيد غير كافي.

---

### transfer(double amount, Account to)

```java
if (!withdraw(amount)) return false;
if (!to.deposit(amount)) {
    deposit(amount); // rollback
    return false;
}
return true;
```

**Explanation:**

* يسحب من الحساب المرسل.
* يودع في الحساب المستقبل.
* في حالة فشل الإيداع يتم Rollback.
 يحقق Atomic Transaction مثل قواعد البيانات.

---

##  Interest Strategy (Strategy Pattern)
* فصل منطق حساب الفائدة عن الحساب نفسه.
* السماح بتغيير طريقة الحساب بدون تعديل Account.

### InterestStrategy Interface

```java
void calculateInterest(Account account);
double getInterestRate();
```

### Interest Methods inside Account

* setInterestStrategy(InterestStrategy strategy) → ربط الحساب بطريقة الفائدة المناسبة.
* applyInterest() → ينفذ interestStrategy.calculateInterest(this) ليحسب الفائدة حسب النوع.

SOLID → OCP يمكن تغيير طريقة حساب الفائدة لكل حساب دون تعديل الكلاس الأساسي Account.

### Why Account is passed as parameter?
* إعادة استخدام الاستراتيجية مع أي حساب.
* تحقيق DIP و Reusability.

-- إذا جعلنا الاستراتيجية مرتبطة بـ Account واحد، لن نتمكن من إعادة استخدامها مع حسابات أخرى.

---

##  Subclasses
-- Overriding Methods-> يمكن تغيير سلوك withdraw() أو غيره حسب نوع الحساب.

### CurrentAccount

* يسمح بالسحب بأكثر من الرصيد (Overdraft).
* يستخدم NoInterest.

```java
if (getBalance() + overdraftLimit >= amount)
```

---

### SavingAccount

* يستخدم SavingInterest.
* يرث السلوك الافتراضي.
* الفائدة شهرية.

---

### FixedDepositAccount

* يمنع السحب قبل انتهاء المدة.

```java
return false;
```

* يستخدم FixedDepositInterest.

---

&& كل subclass يحدد نوعه ويضع استراتيجية الفائدة الخاصة به
: مثال:
 setInterestStrategy(new SavingInterest(interestRate))

##  Builder Pattern
* تسهيل إنشاء objects للحسابات بدون الحاجة لكتابة constructor طويل.


- لكل نوع حساب يوجد Builder خاص به: CurrentAccountBuilder SavingAccountBuilder FixedDepositAccountBuilder كل Builder يحتوي على attributes مطابقة للـ Account. كل attribute له setter method:
### Example
```java
CurrentAccount account = new CurrentAccountBuilder()
    .setAccountID(1)
    .setCustomerID(100)
    .setCurrency("USD")
    .setBalance(5000)
    .setOverdraftLimit(1000)
    .build();
```

**Benefit (SOLID – OCP):**

* إضافة نوع حساب جديد بدون تعديل الكود القديم.

---

##  AccountService
* Business Logic Layer.
* فصل الإدارة عن الـ Models.

### Attributes

```java
private final List<Account> accounts;
```

### Methods

* addAccount → إضافة حساب للنظام.
* getAllAccounts → عرض كل الحسابات.
* deposit → تنفيذ إيداع.
* transfer → تحويل آمن مع rollback.
* applyInterest → تطبيق الفائدة.

---

##  SOLID Principles Applied

### SRP

* Account → بيانات + عمليات.
* AccountService → إدارة.
* Builders → إنشاء.

### OCP

* إضافة حساب أو فائدة جديدة بدون تعديل الكود الأساسي.

### LSP

* أي subclass يمكن استخدامه بدل Account.

### ISP

* AccountOperations تحتوي فقط العمليات الضرورية.

### DIP

* Account يعتمد على InterestStrategy interface.

---

## UML Overview

* Account (abstract)
* Subclasses: Current, Saving, FixedDeposit
* InterestStrategy + implementations
* AccountService

