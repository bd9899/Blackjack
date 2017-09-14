// This class represents one playing card.
public class Card
{
	// Card suits 
	public static final int SPADES   = 0;
	public static final int HEARTS   = 1;
	public static final int CLUBS    = 2;
	public static final int DIAMONDS = 3;

	// Card faces 
	public static final int ACE      = 1;
	public static final int TWO      = 2;
	public static final int THREE    = 3;
	public static final int FOUR     = 4;
	public static final int FIVE     = 5;
	public static final int SIX      = 6;
	public static final int SEVEN    = 7;
	public static final int EIGHT    = 8;
	public static final int NINE     = 9;
	public static final int TEN      = 10;
	public static final int JACK     = 11;
	public static final int QUEEN    = 12;
	public static final int KING     = 13;
	public static final int ACE11    = 14;


	// fields
	private int suit;
	private int face;
	
	// This constructor builds a card with the given suit and face, turned face down.
	public Card(int cardSuit, int cardFace)
	{
		suit = cardSuit;
		face = cardFace;
		
	}

	// This method retrieves the suit (spades, hearts, etc.) of this card.
	public int getSuit()
	{
		return suit;
	}
	
	// This method retrieves the face (ace through king) of this card.
	public int getFace()
	{
		return face;
	}
	
	// This method retrieves the numerical value of this card
	// (usually same as card face, except 1 for ace and 10 for jack/queen/king)
	public int getValue()
	{
		if(face > 0 && face < 11)
			return face;
		
		else if(face == 14)
			return 11;
		
		else 
			return 10;
				
	}
	
	//Sets the value of ace to 11 if called
	public void setValueOfAce(){
		
		if(this.getValue() == 1)
			this.face = 14;
		
		else
			System.out.println("Not an ace");
	}
}
	