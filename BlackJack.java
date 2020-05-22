import java.util.*;
import java.text.*;
import javax.swing.*;
import java.awt.event.*;

public class BlackJack {

	Deck deck;
	Hand playerHand;
	Hand bankHand;
	boolean gameFinished; 

	public BlackJack ()
	{
		deck = new Deck(2);
		playerHand = new Hand();
		bankHand = new Hand();
		gameFinished = false;
	}

	public void reset() /*throws EmptyDeckException*/ {
		bankHand.clear();
		playerHand.clear();
		gameFinished = false;

		for(int i =0; i < 2; i++) {
			try {
				if(i == 0)
				{
					bankHand.add(deck.draw());
				}
				playerHand.add(deck.draw());
			} catch (EmptyDeckException ex) {
				JOptionPane.showMessageDialog(null, "The Deck is empty", "Error", JOptionPane.ERROR_MESSAGE);
				//System.err.println(ex.getMessage());
				System.exit(-1);
			}
		}
	}

	public String getPlayerHandString() {
		return this.playerHand.toString();
	}

	public String getBankHandString() {
		return this.bankHand.toString();
	}

	public int PlayerBest() {
		return this.playerHand.best();
	}

	public int BankBest() {
		return this.bankHand.best();
	}
	public boolean isGameFinished() {
		if(this.gameFinished == true) {
			return true;
		}else {
			return false;
		}
	}
	public boolean isPlayerWinner() {
		int playerBest = PlayerBest();

		if(this.gameFinished == true) {
			if(playerBest != 0) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}

	public boolean isBankWinner() {
		int bankBest = BankBest();

		if(this.gameFinished == true) {
			if(bankBest != 0) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}

	public void playerDrawAnotherCard() throws EmptyDeckException {
		int playerBest;

		if(this.gameFinished == false) {
			try {
					this.playerHand.add(deck.draw());
					this.playerHand.count();
					playerBest = PlayerBest();
						
					if(playerBest == 0 || playerBest == 21) {
						this.gameFinished = true;
					}

			} catch (EmptyDeckException ex) {
				JOptionPane.showMessageDialog(null, "The Deck is empty", "Error", JOptionPane.ERROR_MESSAGE);
				//JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				//System.err.println(ex.getMessage());
				System.exit(-1);
			}
		}
	}

	public void bankLastTurn() throws EmptyDeckException {
		int bankBest = BankBest();
		int playerBest = PlayerBest();

		if(this.gameFinished == false && playerBest !=0) {
			while(bankBest < playerBest && bankBest !=0) {

				try {

					bankHand.add(deck.draw());
					bankHand.count();
					bankBest = BankBest();

				}catch (EmptyDeckException ex) {
					JOptionPane.showMessageDialog(null, "The Deck is empty", "Error", JOptionPane.ERROR_MESSAGE);
					//JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					//System.err.println(ex.getMessage());
					System.exit(-1);
				}
			}
		}
		this.gameFinished = true;
	}

	public List<Card> getPlayerCardList() {
		List<Card> originalList = playerHand.getCardList();
		LinkedList<Card> copyList = new LinkedList<Card>(originalList); //copy !
		return copyList;
	}

	public List<Card> getBankCardList() {
		List<Card> originalList = bankHand.getCardList();
		LinkedList<Card> copyList = new LinkedList<Card>(originalList); //copy !
		return copyList;
	}
}