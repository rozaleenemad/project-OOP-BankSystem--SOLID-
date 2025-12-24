package builder.card;

import enums.CardProvider;
import enums.CardStatus;
import enums.CardType;
import models.Card;
import models.CreditCard;
import models.DebitCard;

import java.util.Date;

public class CardBuilder {

    private int cardID;
    private int customerID;
    private int accountID;
    private String cardNumber;
    private CardType cardType;
    private CardProvider provider;
    private Date expiryDate;
    private String CVV;
    private CardStatus status;
    private Date issueDate;

    public CardBuilder setCardID(int cardID) {
        this.cardID = cardID;
        return this;
    }

    public CardBuilder setCustomerID(int customerID) {
        this.customerID = customerID;
        return this;
    }

    public CardBuilder setAccountID(int accountID) {
        this.accountID = accountID;
        return this;
    }

    public CardBuilder setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public CardBuilder setCardType(CardType cardType) {
        this.cardType = cardType;
        return this;
    }

    public CardBuilder setProvider(CardProvider provider) {
        this.provider = provider;
        return this;
    }

    public CardBuilder setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public CardBuilder setCVV(String CVV) {
        this.CVV = CVV;
        return this;
    }

    public CardBuilder setStatus(CardStatus status) {
        this.status = status;
        return this;
    }

    public CardBuilder setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public Card build() {

        if (cardType == CardType.CREDIT) {
            return new CreditCard(
                    cardID, customerID, accountID, cardNumber,
                    provider, expiryDate, CVV, status, issueDate
            );
        }

        if (cardType == CardType.DEBIT) {
            return new DebitCard(
                    cardID, customerID, accountID, cardNumber,
                    provider, expiryDate, CVV, status, issueDate
            );
        }

        throw new IllegalStateException("Invalid Card Type");
    }
}
