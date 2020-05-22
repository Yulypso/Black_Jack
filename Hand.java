import java.util.LinkedList;
import java.util.*;

public class Hand {

	private LinkedList<Card> cardList;
	private LinkedList<Integer> integerList;


	//constructor 
	public Hand() {
		this.cardList = new LinkedList<Card>();
		this.integerList = new LinkedList<Integer>();
		this.integerList.add(0);
	}

	public String toString() {
		return cardList.toString();
	}
	

	public void add(Card card) {
		cardList.add(card); 
	}

	public void clear() {
        cardList.clear();
	}

	public List<Integer> count() { //CONTINUER
		integerList.clear();
		integerList.add(0);

		int val = 0;
		int dualA = 0;


	for(Card c : cardList) {
		for(int i=0; i<integerList.size(); i++) {
	
				val = integerList.get(i);
				integerList.set(i, val + c.getPoints());

				if(c.getPoints() == 1) 
				{
					integerList.add(val+11); //integerList.get(i)+10
					dualA++;
					if(dualA != 2)
					{
						break;	
					}
				}
			}
			val=0;
		}
		return integerList;
	}

	public int best() {
		int val;
		int best = 0;

		for(int i=0; i < integerList.size(); i++) {
			val = integerList.get(i);
			if(val > best && val <= 21) {
				best = val;
			}
		}
		return best;
	}

	public List<Card> getCardList() {
		return cardList;
	}
}