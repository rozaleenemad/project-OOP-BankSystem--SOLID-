package strategy.card;

import models.Card;

public interface CardFeeStrategy {
    void applyFee(Card card);
}
