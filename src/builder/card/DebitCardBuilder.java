package builder.card;

import enums.CardProvider;
import enums.CardStatus;
import models.DebitCard;

import java.util.Date;

public class DebitCardBuilder {

    private int cardID;
    private int customerID;
    private int accountID;
    private String cardNumber;
    private CardProvider provider;
    private Date expiryDate;
    private String CVV;
    private CardStatus status = CardStatus.INACTIVE;
    private Date issueDate = new Date();

    public DebitCardBuilder setCardID(int cardID) {
        this.cardID = cardID;
        return this;
    }

    public DebitCardBuilder setCustomerID(int customerID) {
        this.customerID = customerID;
        return this;
    }

    public DebitCardBuilder setAccountID(int accountID) {
        this.accountID = accountID;
        return this;
    }

    public DebitCardBuilder setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public DebitCardBuilder setProvider(CardProvider provider) {
        this.provider = provider;
        return this;
    }

    public DebitCardBuilder setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public DebitCardBuilder setCVV(String CVV) {
        this.CVV = CVV;
        return this;
    }

    public DebitCardBuilder setStatus(CardStatus status) {
        this.status = status;
        return this;
    }

    public DebitCardBuilder setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public DebitCard build() {
        return new DebitCard(
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
