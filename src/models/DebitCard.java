package models;
import enums.CardProvider;
import enums.CardStatus;
import enums.CardType;
import java.util.Date;
import strategy.card.NoFeeStrategy;

public class DebitCard extends Card {

    public DebitCard(int cardID, int customerID, int accountID, String cardNumber,
                     CardProvider provider, Date expiryDate, String CVV, CardStatus status, Date issueDate) {
        super(cardID, customerID, accountID, cardNumber, CardType.DEBIT, provider, expiryDate, CVV, status, issueDate);
        this.feeStrategy = new NoFeeStrategy();
    }
}
