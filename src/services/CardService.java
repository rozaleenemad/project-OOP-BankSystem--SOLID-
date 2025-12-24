package services;

import models.Card;

public class CardService {

    public void activateCard(Card card) {
        card.activate();
        System.out.println("Card activated: " + card.getCardNumber());
    }

    public void blockCard(Card card) {
        card.block();
        System.out.println("Card blocked: " + card.getCardNumber());
    }

    public void applyFee(Card card) {
        card.applyFee();
    }

    public void printCard(Card card) {
        card.displayCardInfo();
    }
}
