// This class represents the deck of cards from which cards are dealt to players.
public class Deck
{
	private Card[] deck;
	
	// This constructor builds a deck of 52 cards.
	
	public Deck()
	{
		deck = new Card[52];
		
		for(int i = 1; i <= 13; i++){
			deck[i-1] = new Card(0,i);
		}
		for(int i = 1; i <= 13; i++){
			deck[12 + i] = new Card(1,i);
		}
		for(int i = 1; i <= 13; i++){
			deck[25+ i] = new Card(2,i);
		}
		for(int i = 1; i <= 13; i++){
			deck[38 + i] = new Card(3,i);
		}	
	}

	
	// This method takes the top card off the deck and returns it.
	public Card deal()
	{	
		
		Card dealt = deck[0];
		
		Card[] temp = new Card[deck.length - 1];
		
		if(temp.length == 0) {
			deck = null;
			return dealt;
		}
		else {
			for(int i = 0; i < temp.length; i++){
			temp[i] = deck[i+1];
		}
		
		deck = temp;
		return dealt;
		}
	}
	
	
	// this method returns true if there are no more cards to deal, false otherwise
	public boolean isEmpty()
	{
		if(deck == null)
			return true;
		else
			return false;
	}
	
	//this method puts the deck in some random order
	public void shuffle(){
		
		int randomIndex;
		Card temp;
		
		for(int i = 0; i < deck.length; i++){
			temp = deck[i];
			randomIndex = (int)(Math.random() * 52);
			deck[i] = deck[randomIndex];
			deck[randomIndex] = temp;	
		}
	}
}
