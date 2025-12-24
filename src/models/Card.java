package models;

import enums.CardProvider;
import enums.CardStatus;
import enums.CardType;
import interfaces.CardOperations;
import java.util.Date;
import strategy.card.CardFeeStrategy;
import strategy.card.NoFeeStrategy;

public abstract class Card implements CardOperations {

    protected int cardID;
    protected int customerID;
    protected int accountID;
    protected String cardNumber;
    protected CardType cardType;
    protected CardProvider provider;
    protected Date expiryDate;
    protected String CVV;
    protected CardStatus status;
    protected Date issueDate;

    protected CardFeeStrategy feeStrategy;

    // Constructor
    public Card() {
        this.feeStrategy = new NoFeeStrategy();
    }

    public Card(int cardID, int customerID, int accountID, String cardNumber,
                CardType cardType, CardProvider provider, Date expiryDate,
                String CVV, CardStatus status, Date issueDate) {

        this.cardID = cardID;
        this.customerID = customerID;
        this.accountID = accountID;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.provider = provider;
        this.expiryDate = expiryDate;
        this.CVV = CVV;
        this.status = status;
        this.issueDate = issueDate;

        this.feeStrategy = new NoFeeStrategy();
    }

    // Getters & Setters
    public int getCardID() { return cardID; }
    public int getCustomerID() { return customerID; }
    public int getAccountID() { return accountID; }
    public String getCardNumber() { return cardNumber; }
    public CardType getCardType() { return cardType; }
    public CardProvider getProvider() { return provider; }
    public Date getExpiryDate() { return expiryDate; }
    public String getCVV() { return CVV; }
    public CardStatus getStatus() { return status; }
    public Date getIssueDate() { return issueDate; }

    public void setFeeStrategy(CardFeeStrategy feeStrategy) { this.feeStrategy = feeStrategy; }

    // Strategy
    public void applyFee() { this.feeStrategy.applyFee(this); }

    // Operations
    @Override
    public void activate() {
        if (status != CardStatus.BLOCKED && status != CardStatus.EXPIRED) {
            this.status = CardStatus.ACTIVE;
        }
    }

    @Override
    public void block() { this.status = CardStatus.BLOCKED; }

    public void displayCardInfo() {
        System.out.println("Card: " + cardNumber + ", Type: " + cardType +
                ", Provider: " + provider + ", Status: " + status);
    }
}
