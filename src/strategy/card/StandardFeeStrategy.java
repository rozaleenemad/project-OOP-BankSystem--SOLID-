package strategy.card;

import models.Card;

public class StandardFeeStrategy implements CardFeeStrategy {
    @Override
    public void applyFee(Card card) {
        System.out.println("Standard fee applied to card: " + card.getCardNumber());
    }
}