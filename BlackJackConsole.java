import java.util.*;
import java.text.*;


public class BlackJackConsole {

	public BlackJackConsole() {
		System.out.println("Welcome to the BlackJack table. Let's play !");

		//This is an Array of two cards
		Deck deck = new Deck(4);
		Hand hand = new Hand();


		//System.out.println("Here is the deck "+ deck +"\n");
		System.out.println("Your hand is currently: "+ hand);

		for(int i =0; i < 3; i++) {

			try {
				hand.add(deck.draw());
			} catch (EmptyDeckException ex) {
				System.err.println(ex.getMessage());
				System.exit(-1);
			}

			/*try {
				Card c = deck.draw();
				System.out.println("This card is a "+ c +" worth " + c.getPoints() + " points");
				System.out.print("It's a ");

			switch(c.getColorSymbole()) { //Ok from Java 1.7
				case "♡": System.out.print("heart"); break;
				case "♠": System.out.print("spade"); break;
				case "♣": System.out.print("club"); break;
				case "♢": System.out.print("diamond"); break;
			}

				if(c.getValueSymbole().matches("[JQK]")) { //Is the value symbole a J or a Q or a K ?
					System.out.println(" and a face !");
				} else {
					System.out.println(" and it's not a face.");
				}
			} catch (EmptyDeckException ex) {
				System.err.println(ex.getMessage());
				System.exit(-1);
			}*/
		}

		System.out.print("Your hand is currently : "+ hand);
		System.out.println(" : "+ hand.count());
		System.out.println("The best score is: "+ hand.best());
		

		//System.out.println("Here is the deck after drawing "+ deck +"\n");
		hand.clear();
		System.out.println("Your hand is currently : "+ hand);

		
		}

	public static void main(String[] args) throws EmptyDeckException{
		//new BlackJackConsole();
		System.out.print("\033[H\033[2J");
		BlackJack blackJack = new BlackJack();
		blackJack.reset();
		System.out.println("The bank draw : "+ blackJack.getBankHandString() + " : "+ blackJack.bankHand.count());
		System.out.println("You draw : " + blackJack.getPlayerHandString()+ " : "+ blackJack.playerHand.count());

		Scanner scan = new Scanner(System.in);
		boolean inGame = true;

		while(inGame)
		{
			//System.out.println("Here is the deck "+ blackJack.deck +"\n");
			System.out.println("Do you want another card ? [y/n]");
			String choice = scan.next();

			switch (choice) {

				default : 
					System.out.println("Unknown command. Type h for help");
					break;

				case "y" :
				try {
					blackJack.playerDrawAnotherCard();
				}catch (EmptyDeckException ex) {
				System.err.println(ex.getMessage());
				System.exit(-1);
			}
					
					//System.out.println("Here is the deck "+ deck +"\n");
					System.out.println("Your new hand : "+ blackJack.getPlayerHandString() +" : " + blackJack.playerHand.count());
					break;

				case "n" : 
					try {
					blackJack.bankLastTurn();
				}catch (EmptyDeckException ex) {
				System.err.println(ex.getMessage());
				System.exit(-1);
			}
					System.out.println("The bank draw cards. New hand : " + blackJack.getBankHandString() + " : " + blackJack.bankHand.count());
					System.out.println("Player best : " + blackJack.PlayerBest());
					System.out.println("Bank best : " + blackJack.BankBest());


					if(blackJack.isPlayerWinner() == true && blackJack.isBankWinner() == true) {
						
						if(blackJack.PlayerBest() > blackJack.BankBest()) {
							System.out.println("Player won !");
						}
						else if(blackJack.PlayerBest() == blackJack.BankBest()) {
							System.out.println("Draw !");
						}
						else {
							System.out.println("Bank won !");
						}

					} 
					else if(blackJack.isBankWinner() == false && blackJack.isPlayerWinner() == true) {
						System.out.println("Player won !");
					} 
					else if(blackJack.isPlayerWinner() == false && blackJack.isBankWinner() == true) {
						System.out.println("Bank won !");
					}





					inGame = false;

					break;

				case "h" : 
					System.out.println("y : draw a card ");
					System.out.println("n : stop ");
					break; 
			}

		}
	}
}