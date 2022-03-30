/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tonym
 */
public abstract class Person {
    
    protected String name;
    protected Hand hand;
    protected int balance;

    public Person() {
    }

    public void drawCard(Deck deck) {
        hand.add(deck.removeCard());
        if (hand.has21() && hand.size() > 2) {
            System.out.println(name + " has " + hand);
        }
        if (hand.busted()) {
            System.out.println(name + " busts with " + hand + "!");
        }
    }
    
    public String getName() {
        return name;
    }
    

    

    public abstract void haveTurn(Deck deck);

    @Override
    public String toString() {
        return name + " has " + hand;
    }
    
}
