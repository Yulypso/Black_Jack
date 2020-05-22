import java.util.*;

public class Deck {

	private LinkedList<Card> cardList;

	//constructor
	public Deck(int nbBox) {
		this.cardList = new LinkedList<>(); //allocation de mémoire

		for(int i=0; i< nbBox; i++) 
			for(Color c : Color.values())
				for(Value v : Value.values())
					this.cardList.add(new Card(v, c));

		
		Collections.shuffle(cardList); //permet de mélanger une liste 
	  /*Collections.sort(cardList); //permet de trier une liste
		Collections.swap(cardList); //permet d'inverser une liste
		Collections.reverse(cardListe, int i, int j); //permet d'échanger deux valeurs
	  */
	}

	public String toString() {
		return cardList.toString(); //affiche toute la liste
	}

	public Card draw() throws EmptyDeckException {

		if(cardList.isEmpty()) throw new EmptyDeckException("The deck is empty");
		else {
			Card currentCard = cardList.pollFirst(); //retrieves (recupere) and remove from the list 
			return currentCard;
		}
	}
}