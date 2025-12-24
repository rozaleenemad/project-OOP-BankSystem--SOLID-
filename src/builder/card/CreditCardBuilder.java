package builder.card;

import enums.CardProvider;
import enums.CardStatus;
import models.CreditCard;

import java.util.Date;

public class CreditCardBuilder {

    private int cardID;
    private int customerID;
    private int accountID;
    private String cardNumber;
    private CardProvider provider;
    private Date expiryDate;
    private String CVV;
    private CardStatus status = CardStatus.INACTIVE;
    private Date issueDate = new Date();

    public CreditCardBuilder setCardID(int cardID) {
        this.cardID = cardID;
        return this;
    }

    public CreditCardBuilder setCustomerID(int customerID) {
        this.customerID = customerID;
        return this;
    }

    public CreditCardBuilder setAccountID(int accountID) {
        this.accountID = accountID;
        return this;
    }

    public CreditCardBuilder setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public CreditCardBuilder setProvider(CardProvider provider) {
        this.provider = provider;
        return this;
    }

    public CreditCardBuilder setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public CreditCardBuilder setCVV(String CVV) {
        this.CVV = CVV;
        return this;
    }

    public CreditCardBuilder setStatus(CardStatus status) {
        this.status = status;
        return this;
    }

    public CreditCardBuilder setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public CreditCard build() {
        return new CreditCard(
                cardID,
                customerID,
                accountID,
                cardNumber,
                provider,
                expiryDate,
                CVV,
                status,
                issueDate
        );
    }
}
