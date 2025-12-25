# Card Module Documentation

## Supported Card Types

* **Credit Card**
* **Debit Card**
* **(Extensible) Prepaid Card**

Each card type shares common behavior but may differ in fee rules and usage logic.

---


## Card Lifecycle

1. Card is created → `INACTIVE / PENDING`
2. Card is activated → `ACTIVE`
3. Card may be blocked → `BLOCKED`
4. Card may expire → `EXPIRED`

---

## Interface: CardOperations

Defines the core operations that every card must support.
يحدد العمليات الأساسية لأي بطاقة

### Methods

* `void activate()`
→ لتفعيل البطاقة.
* `void block()`
→ لحظر البطاقة.

### Design Principle

* **ISP (Interface Segregation Principle)**: The interface contains only card-related operations.
أي بطاقة يجب أن تعرف العمليات الخاصة بها.
---

## Enums

### CardType

* `DEBIT`
* `CREDIT`
* `PREPAID`

Defines the card category and determines which subclass is instantiated.

### CardStatus

* `PENDING`
* `INACTIVE`
* `ACTIVE`
* `BLOCKED`
* `EXPIRED`

Used to validate allowed operations (e.g., cannot activate a blocked card).

### CardProvider

* `VISA`
* `MASTERCARD`

Defines the external card provider.

---

## Abstract Class: Card
* يسمح لأي نوع بطاقة أن يتعامل على أنه Card.
* يحتوي على attributes مشتركة لكل البطاقات
* يحتوي على methods عامة متاحة لكل subclass
->activate(), block(), displayCardInfo(), applyFee().
The base class for all card types. It cannot be instantiated directly.

### Attributes

* `cardID` – Unique card identifier
* `customerID` – Card owner
* `accountID` – Linked bank account
* `cardNumber` – Card number
* `cardType` – Type of card (Debit / Credit)
* `provider` – Card provider
* `expiryDate` – Expiration date
* `CVV` – Security code
* `status` – Current card status
* `issueDate` – Issue date
* `feeStrategy` – Strategy for applying fees

- Protected CardFeeStrategy feeStrategy
-> كل بطاقة قد يكون لها طريقة تطبيق رسوم مختلفة.

### Constructors

* Default constructor → assigns `NoFeeStrategy`
* Full constructor → initializes all attributes

---

### Core Methods

#### activate()

* Activates the card only if it is not `BLOCKED` or `EXPIRED`
* Changes status to `ACTIVE`

#### block()

* Blocks the card immediately
* Changes status to `BLOCKED`

#### applyFee(Card card)
لتطبيق الرسوم حسب نوع البطاقة.
* Executes `feeStrategy.applyFee(this)`
* Fee logic depends on card type

#### displayCardInfo()

* Displays card number, type, provider, and status

### setFeeStrategy(CardFeeStrategy strategy)
 → ربط البطاقة بطريقة تطبيق الرسوم.

---

## Strategy Pattern: Fee Handling

### Interface: CardFeeStrategy

* `void applyFee(Card card)`

### Implementations

* **CreditFeeStrategy** → applies credit card fees
* **StandardFeeStrategy** → general-purpose fee strategy
* **NoFeeStrategy** → no fees applied

### Design Benefits

* **DIP:** Card depends on abstraction, not concrete fee logic
* **OCP:** New fee strategies can be added without modifying Card

---

## Card Subclasses

### CreditCard

* Card type: `CREDIT`
* Uses `CreditFeeStrategy`
* Can be extended later for credit limits or rewards

### DebitCard

* Card type: `DEBIT`
* Uses `NoFeeStrategy`
* Linked directly to a bank account

---

## Builder Pattern

* Avoid long constructors
* Improve readability and safety
* Encapsulate object creation logic

### Builders

* `CardBuilder` → general builder that selects card type
* `CreditCardBuilder`
* `DebitCardBuilder`

### Example

```java
Card card = new CardBuilder()
    .setCardID(1)
    .setCustomerID(100)
    .setAccountID(10)
    .setCardType(CardType.CREDIT)
    .setProvider(CardProvider.VISA)
    .setExpiryDate(expiry)
    .build();
```

### build()

* Creates the correct subclass based on `CardType`
* Throws exception for invalid type

---

## CardService


Acts as the business layer responsible for managing cards.

### Responsibilities

**activateCard(Card card)**
 → يفعل البطاقة باستخدام card.activate().
**blockCard(Card card)**
 → يحظر البطاقة باستخدام card.block().
**applyFee(Card card)**
 → ينفذ card.applyFee() باستخدام الاستراتيجية الخاصة.
 **printCard(Card card)**
  → يعرض بيانات البطاقة باستخدام card.displayCardInfo()
Display card information

### SOLID Compliance

* **SRP:** Handles only card-related business logic
* **DIP:** Depends on abstract `Card` type
* **OCP:** Supports new card types or fee strategies without modification

---

## SOLID Principles Summary

### SRP

Each class has a single responsibility:

* Card → card data and operations
* CardService → business logic
* Builders → object creation

### OCP

New card types or fee strategies can be added without modifying existing code.

### LSP

All subclasses (CreditCard, DebitCard) can replace `Card` safely.

### ISP

CardOperations contains only essential operations.

### DIP

Card depends on `CardFeeStrategy` abstraction.


