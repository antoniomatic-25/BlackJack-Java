public class Player extends Person {

    public Player(String name, int balance) {
        this.name = name;
        hand = new Hand();
        this.balance = balance;
    }


    @Override
    public void haveTurn(Deck deck) {
        char choice;
        while (!hand.busted() && !hand.has21() && (choice = readChoice()) != 's') {
            switch (choice) {
            case 'h': drawCard(deck); break;
            default: help(); break;
            }
        }
    }
    
    public int bet(int amount) {
        int ogBalance = balance;
        balance -= amount;
        return ogBalance;
    }

    private char readChoice() {
        System.out.print(name + " has " + hand + " - choice (h/s): ");
        return In.nextChar();
    }
    
    public int getBalance() {
        return balance;
    }

    public void decide(Hand dealerHand) {
        if (hand.beats(dealerHand))
            System.out.println(name + " wins with " + hand + "!");
        else if (hand.push(dealerHand))
            System.out.println(name + " ties with the dealer, bets are returned");
        else if (!hand.busted())
            System.out.println(name + " loses with " + hand + ".");
    }

    private void help() {
        System.out.println("Player menu options:");
        System.out.println("h = hit");
        System.out.println("s = stand");
    }

}
