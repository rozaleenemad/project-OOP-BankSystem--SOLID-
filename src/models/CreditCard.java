package models;

import enums.CardProvider;
import enums.CardStatus;
import enums.CardType;
import java.util.Date;
import strategy.card.CreditFeeStrategy;

public class CreditCard extends Card {

    public CreditCard(int cardID, int customerID, int accountID, String cardNumber,
                      CardProvider provider, Date expiryDate, String CVV, CardStatus status, Date issueDate) {
        super(cardID, customerID, accountID, cardNumber, CardType.CREDIT, provider, expiryDate, CVV, status, issueDate);
        this.feeStrategy = new CreditFeeStrategy();
    }
}
