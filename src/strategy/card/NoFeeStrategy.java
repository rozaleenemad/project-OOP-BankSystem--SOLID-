package strategy.card;

import models.Card;

public class NoFeeStrategy implements CardFeeStrategy {
    @Override
    public void applyFee(Card card) {
        // لا شيء يتم
    }
}
