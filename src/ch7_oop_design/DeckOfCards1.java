/*
7.1 Deck of Cards: Design the data structures for a generic deck of cards. Explain how you would
subclass the data structures to implement blackjack. 
 */
package ch7_oop_design;

import java.util.*;

public class DeckOfCards1 {
        public enum Suit {
            CLUBS, DIAMONDS, HEARTS, SPADES
        }

        public abstract class Card {
            private final Suit suit; //shapes of card
            private final int value; // depends on game: could be 1–13 or 1–11 (Blackjack)

            public Card(Suit suit, int value) {
                this.suit = suit;
                this.value = value;
            }

            public Suit getSuit() { return suit; }
            public int getValue() { return value; }

            public abstract int getGameValue(); // subclass defines game-specific value
        }

        public class Deck<T extends Card> {
            private List<T> cards;
            private int dealtIndex = 0;

            public Deck(List<T> cards) {
                this.cards = cards;
            }

            public void shuffle() {
                Collections.shuffle(cards);
                dealtIndex = 0;
            }

            public T dealCard() {
                if (dealtIndex >= cards.size()) return null;
                return cards.get(dealtIndex++);
            }

            public int remainingCards() {
                return cards.size() - dealtIndex;
            }
        }

        public class Hand<T extends Card> {
            protected List<T> cards = new ArrayList<>();

            public void addCard(T card) {
                cards.add(card);
            }

            public List<T> getCards() {
                return cards;
            }

            public int score() {
                int total = 0;
                for (T card : cards) {
                    total += card.getGameValue();
                }
                return total;
            }
        }

        public class BlackjackCard extends Card {
            public BlackjackCard(Suit suit, int value) {
                super(suit, value);
            }

            @Override
            public int getGameValue() {
                int val = getValue();
                if (val > 10) return 10; // Face cards
                return val;
            }

            public boolean isAce() {
                return getValue() == 1;
            }

            public int minValue() {
                return isAce() ? 1 : getGameValue();
            }

            public int maxValue() {
                return isAce() ? 11 : getGameValue();
            }
        }

        public class BlackjackHand extends Hand<BlackjackCard> {

            @Override
            public int score() {
                int min = 0;
                int aces = 0;

                for (BlackjackCard card : cards) {
                    min += card.minValue();
                    if (card.isAce()) aces++;
                }

                // Try to upgrade some Aces to 11 if it doesn't bust
                int score = min;
                while (aces > 0 && score + 10 <= 21) {
                    score += 10;
                    aces--;
                }

                return score;
            }

            public boolean isBusted() {
                return score() > 21;
            }

            public boolean is21() {
                return score() == 21;
            }
        }
}

