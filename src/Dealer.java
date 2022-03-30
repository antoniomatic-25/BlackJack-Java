import java.util.*;

public class Dealer extends Person{
    public static void main(String[] args) { new Dealer().use(); }
    private Deck deck;
    private LinkedList<Player> players = new LinkedList<Player>();

    public Dealer() {
        deck = new Deck();
        hand = new Hand();
        //players.add(new Player("Nathan", 1000));
        //players.add(new Player("Jessica", 1000));
        //players.add(new Player("Josh", 1000));
        this.name = "Dealer";
        this.balance = 50000;
    }

    private void use() {
        //bet();
        System.out.print("How many players are there?: ");
        String numPlayers = In.nextLine();
        int intPlayers = Integer.parseInt(numPlayers);
        String name;
        for(int i = 0; i < intPlayers; i++) {
            System.out.print("What is player" + (i + 1) + "'s name?: ");
            name = In.nextLine();
            players.add(new Player(name, 1000));
        }
        
        
        
        shuffle();
        deal();
        drawCard(deck);
        deal();
        drawCard(deck);
        dealerHandInitial();
        for (Player player : players) {
            System.out.println(player);
        }
        if (!hand.isBlackjack())
            goRound();
        decide();
    }

    private void shuffle() {
        deck.shuffle();
    }
    
    private int betAmount() {
        System.out.print("Enter the bet amount:");
        return In.nextInt();
    }
    
    private void bet() {		
        int betAmount = betAmount();
        Player player = findPlayer(players, "George");
        int ogAmount = player.bet(betAmount);
                
                
                
        System.out.print("\n");
        System.out.println("Bets received");
        System.out.println(player.getName() + "'s balance was: $" + ogAmount);
        System.out.println(player.getName() + "'s current balance is: $" + player.getBalance());
        System.out.print("\n");
    }
    
    private Player findPlayer(LinkedList<Player> players, String name) {
        int i = 0;
        for(Player player : players) {
                if(name == player.getName()) {
                        int index = i; 
                        return players.get(index);
                }
                i++;			
        }
        System.out.println("That player does not exist.\n");
        return null;
    }
    
    private void dealerHandInitial() {
        hand.dealerHandInitial();
    }
    

    private void deal() {
        for (Player player : players)
            player.drawCard(deck);
    }


    private void goRound() {
        //System.out.println(this);
        for (Player player : players)
            player.haveTurn(deck);
        haveTurn(deck);
    }
    
    @Override
    public void haveTurn(Deck deck) {
        while (hand.value() < 17)
            drawCard(deck);
    }

    private void decide() {
        
        if (!hand.busted() && !hand.has21()) {
            System.out.println(this);
        }            
        for (Player player : players)
            player.decide(hand);
    }
    
}