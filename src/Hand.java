import java.util.*;

public class Hand {
    private LinkedList<Card> cards = new LinkedList<Card>();

    public Hand() {
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int value() {
        int handSum = 0;
        int cardNum;
        int numAces = 0;
        
        for (Card card : cards) {
            cardNum = card.value();
            
            if(cardNum == 1) {
                numAces++;
                handSum += 11;
            } else if (cardNum > 10) {
                handSum += 10;
            } else {
                handSum += cardNum;
            }
        }
        
        while (handSum > 21 && numAces > 0) {
            handSum -= 10;
            numAces--;
        }
        return handSum;
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && has(1) && has(10);
    }
    
    public boolean has21() {
        return value() == 21;
    }

    public boolean has(int value) {
        for (Card card : cards)
            if (card.hasValue(value))
                return true;
        return false;
    }

    public boolean busted() {
        return value() > 21;
    }
    
    public int size() {
        return cards.size();
    }
    
    public boolean push(Hand other) {
        return value() == other.value();
    }

    public boolean beats(Hand other) {
        return !busted() && (value() > other.value() || other.busted());
    }
    
    public void dealerHandInitial() {
        System.out.println("Dealer has cards: hidden and " + cards.get(1));
    }
    
    

    @Override
    public String toString() {
        String s = "";
        for (Card card : cards)
            s += card + " ";
        return s.trim() + ": " + value();
    }
}
