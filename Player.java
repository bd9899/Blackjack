public class Player {
	
	private int score;
	private double moneyBet;
	private double money;
	private Card card1;
	private Card card2;
	
	
	public Player(double moneyBet, Card A, Card B){
		this.moneyBet = moneyBet;
		card1 = A;
		card2 = B;
		score = card1.getValue() + card2.getValue();
		money = 10000;
	}
	public int getScore(){
		return score;
	}
	public double getMoneyBet(){
		return moneyBet;
	}
	public Card getCard1(){
		return card1;
	}
	public Card getCard2(){
		return card2;
	}
	public double getMoney(){
		return money;
	}
	public void setScore(int x){
		score = x;
	}
	public boolean moveOn(){
		if(score <= 21){
			return true;
		}
		else 
			return false;
	}
	
	

}
