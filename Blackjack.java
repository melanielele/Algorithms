
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

class Card {
	// Specify data fields for an individual card  
		public static final int SPADES = 0;
		public static final int HEARTS = 1;
		public static final int CLUBS = 2;
		public static final int DIAMONDS = 3;
		
		public static final int ACE = 1;
		public static final int JACK = 11;
		public static final int QUEEN = 12;
		public static final int KING = 13;
		
		int suit;
		int rank;


		// Fill in constructor method
		public Card(int rank, int suit) {
			this.rank = rank;
			this.suit = suit;
		}	
		// To String code 
		// use character unicode 
		
		public String toString() {
			String s = "";
			switch (this.rank) {
				case ACE : s += "ACE";break;
				case JACK : s += "JACK";break;
				case QUEEN : s+= "QUEEN"; break;
				case KING : s+= "KING"; break;
				default : s += this.rank;			
			 }
			s += " of ";
			switch (this.suit) {
			    case SPADES : s += "SPADES"; break;
			    case HEARTS : s += "HEARTS"; break;
			    case CLUBS : s += "CLUBS"; break;
			    case DIAMONDS : s += "DIAMONDS"; break;
			}
			return s;		
		}
		
		
	}

public class BlackJack {

	 // fill in code here
    // define data members
	public static final int[] SUIT_ORDER = {Card.SPADES, Card.HEARTS, Card.DIAMONDS, Card.CLUBS};
	public static final int[] RANK_ORDER = {Card.ACE, 2, 3, 4, 5, 6, 7, 8, 9, 10, Card.JACK, Card.QUEEN, Card.KING};
	Random random;
	
	
    
    
    public static void buildDeck(ArrayList<Card> deck) {
	// fill in code here
	// Given an empty deck, construct a standard deck of playing cards
        deck.clear();
    		for (int suitPos = 0; suitPos < SUIT_ORDER.length; suitPos++) {
    			for (int rankPos = 0; rankPos < RANK_ORDER.length ; rankPos++) {
    				Card card = new Card(RANK_ORDER[rankPos], SUIT_ORDER[suitPos]);
    				deck.add(card);
    		
    			}
    		}
       
    	
    }

    public static void initialDeal(ArrayList<Card> deck, ArrayList<Card> playerHand, ArrayList<Card> dealerHand){
	// fill in code here
	// Deal two cards from the deck into each of the player's hand and dealer's hand 
    		playerHand.clear();
    		dealerHand.clear();
    	    Random random = new Random();
    		for (int i= 0; i< 2; i++) {
    			Card card = deck.get(random.nextInt(deck.size()));
    			playerHand.add(card); 		
    			deck.remove(card);
    		}
    		
    		for (int i= 0; i< 2; i++) {
    			Card card = deck.get(random.nextInt(deck.size()));
    			dealerHand.add(card);	
    			deck.remove(card);
    		}
    		
    		
    }

    public static void dealOne(ArrayList<Card> deck, ArrayList<Card> hand){
	// fill in code here
	// this should deal a single card from the deck to the hand
    		Random random = new Random(); //?????? do we need to initialize random each time??
    		Card card = deck.get(random.nextInt(deck.size()));
    		hand.add(card);
    		deck.remove(card);
    		
    }

    public static boolean checkBust(ArrayList<Card> hand){
	// fill in code here
	// This should return whether a given hand's value exceeds 21
    	// JQK are all equal to 10 
    	// ACE is either 1 or 11 
    		if (Score(hand) > 21) {
    			return true;
    		}
    		else {
    			return false;
    		}
    }

    public static boolean dealerTurn(ArrayList<Card> deck, ArrayList<Card> hand){
	// fill in code here
	// This should conduct the dealer's turn and
	// Return true if the dealer busts; false otherwise
    	// hits or stand here
    		    int a = Score(hand);
    			while (a < 17) {
    				dealOne(deck,hand);	
    				a = Score(hand);
    			}
    			if (checkBust(hand)){
        			return true;
        		}
    			else {
    			    return false;
    			
    		}
    }

    public static int whoWins(ArrayList<Card> playerHand, ArrayList<Card> dealerHand){
	// fill in code here
	// This should return 1 if the player wins and 2 if the dealer wins
    	// who's sum is bigger if they do not burst
    		if (Score(playerHand) > Score(dealerHand) && (!checkBust(playerHand))){
    			return 1;
    			
    		}
    		else {
    			return 2;
    		}
    		
    		
    		//clear the arraylist maybe here 
    	
    }

    public static String displayCard(ArrayList<Card> hand){
	// fill in code here
	// Return a string describing the card which has index 1 in the hand
    		Card card = hand.get(1);
    		String s = card.toString();
    		return s;
    }

    public static String displayHand(ArrayList<Card> hand){
	// fill in code here
	// Return a string listing the cards in the hand
    		String s = "";
    		for (int i=0; i<hand.size();i++) {
    			Card card = hand.get(i);
    			s += card.toString() + '\n';
    		}
    		return s;
    }

   public static int Score(ArrayList<Card> hand) {
	// calculate the sum of the game
		int sum = 0;
		boolean hasACE = false;
		for (int i=0; i<hand.size();i++) {
			if (hand.get(i).rank == Card.JACK || hand.get(i).rank == Card.QUEEN || hand.get(i).rank == Card.KING) {
				sum += 10;
			}
			else if (hand.get(i).rank >= 2 && hand.get(i).rank <=10) {
				sum += hand.get(i).rank;	
			}
			else if (hand.get(i).rank == Card.ACE) {
				sum += 11;	
			}
			
		}
		
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).rank == Card.ACE) {
				hasACE = true;	
			}	
		}
		
		if (hasACE && (sum >21)) {
			sum -= 10;
		}
		return sum;
   }
    // fill in code here (Optional)
    // feel free to add methods as necessary	

    public static void main(String[] args) {


		int playerChoice, winner;
		String bettingChoice;
		int initialmoney;
		int bettingmoney;
		
		ArrayList<Card> deck = new ArrayList<Card>();
		playerChoice = JOptionPane.showConfirmDialog(
			null, 
			"Ready to Play Blackjack?", 
			"Blackjack",
			JOptionPane.OK_CANCEL_OPTION
		);

		if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
		    System.exit(0);
		
		initialmoney = 50;

		Object[] options = {"Hit","Stand"};
		boolean isBusted;	// Player busts? 
		boolean dealerBusted;
		boolean isPlayerTurn;
	    
		ArrayList<Card> playerHand = new ArrayList<>();
		ArrayList<Card> dealerHand = new ArrayList<>();
		
		
		do{ // Game loop
			buildDeck(deck);  // Initializes the deck for a new game
		    initialDeal(deck, playerHand, dealerHand);
		    isPlayerTurn=true;
		    isBusted=false;
		    dealerBusted=false;
		    bettingmoney = 50;
		    bettingChoice = JOptionPane.showInputDialog(null,
					"Now you have $" + initialmoney + " and your initial hand is \n" + displayHand(playerHand) + "\n Please enter the amount of money you want to bet in this game", 
					"Ready to bet?", 
					JOptionPane.INFORMATION_MESSAGE
		    );
		    
		    if (bettingChoice == null) 
	        System.exit(0);
		    else {
		        bettingmoney = Integer.parseInt(bettingChoice);
		    }
		    
			if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
			    System.exit(0);
			
			
			
		    while(isPlayerTurn){

				// Shows the hand and prompts player to hit or stand
				playerChoice = JOptionPane.showOptionDialog(
					null,
					"Dealer shows " + displayCard(dealerHand) + "\n Your hand is: " 
						+ displayHand(playerHand) + "\n Sum total is: " + Score(playerHand) + "\n What do you want to do?",
					"Hit or Stand",
				   JOptionPane.YES_NO_OPTION,
				   JOptionPane.QUESTION_MESSAGE,
				   null,
				   options,
				   options[0]
				);

				if(playerChoice == JOptionPane.CLOSED_OPTION)
				    System.exit(0);
				
				else if(playerChoice == JOptionPane.YES_OPTION){
				    dealOne(deck, playerHand);
				    isBusted = checkBust(playerHand);
				    if(isBusted){
						// Case: Player Busts
						playerChoice = JOptionPane.showConfirmDialog(
							null,
							"Player has busted!", 
							"You lose", 
							JOptionPane.OK_CANCEL_OPTION
						);
						initialmoney -= bettingmoney;

						if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						    System.exit(0);
						
						isPlayerTurn=false;
				    }
				}
			    
				else{
				    isPlayerTurn=false;
				}
		    }

		    if(!isBusted){ // Continues if player hasn't busted
				dealerBusted = dealerTurn(deck, dealerHand);
				if(dealerBusted){ // Case: Dealer Busts
				    playerChoice = JOptionPane.showConfirmDialog(
				    	null, 
				    	"The dealer's hand: " +displayHand(dealerHand) + "\n Sum total is: " + Score(dealerHand) + "\n \n Your hand: " 
				    		+ displayHand(playerHand) + "\n Sum total is: " + Score(playerHand) + "\nThe dealer busted.\n Congrautions!", 
				    	"You Win!!!", 
				    	JOptionPane.OK_CANCEL_OPTION
				    );	
				    
				    initialmoney += bettingmoney;
 
					if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						System.exit(0);
				
				}
			
			
				else{ //The Dealer did not bust.  The winner must be determined
				    winner = whoWins(playerHand, dealerHand);

				    if(winner == 1){ //Player Wins
						playerChoice = JOptionPane.showConfirmDialog(
							null, 
							"The dealer's hand: " +displayHand(dealerHand) + "\n Sum total is: " + Score(dealerHand)+ "\n \n Your hand: " 
								+ displayHand(playerHand) + "\n Sum total is: " + Score(playerHand)+"\n Congrautions!", 
							"You Win!!!", 
							JOptionPane.OK_CANCEL_OPTION
						);
                        
						initialmoney += bettingmoney;
						
						if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						    System.exit(0);
				    }

				    else{ //Player Loses
						playerChoice = JOptionPane.showConfirmDialog(
							null, 
							"The dealer's hand: " +displayHand(dealerHand) + "\n Sum total is: " + Score(dealerHand)+ "\n \n Your hand: " 
								+ displayHand(playerHand) + "\n Sum total is: " + Score(playerHand)+"\n Better luck next time!", 
							"You lose!!!", 
							JOptionPane.OK_CANCEL_OPTION
						); 
					    
						initialmoney -= bettingmoney;
						
						if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						    System.exit(0);
				    }
				}
		    }
		}while(initialmoney > 0);
		
		JOptionPane.showMessageDialog(null, "Sorry! you don't have money, you lose!");
		System.exit(0);
    }


  
    
}
