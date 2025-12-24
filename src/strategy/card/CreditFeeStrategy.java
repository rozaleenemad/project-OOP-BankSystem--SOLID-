package strategy.card;

import models.Card;

public class CreditFeeStrategy implements CardFeeStrategy {
    @Override

    public void applyFee(Card card) {
        System.out.println("Applying credit card standard fee to " + card.getCardNumber());
        // ممكن تضيف logic لتطبيق الرسوم على الرصيد
    }
}
