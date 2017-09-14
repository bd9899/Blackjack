public class Blackjack {
	
	public static void main(String[] args){  //Ask for amount of players playing
		
		System.out.println("How many players are playing? Only a max of six players can play: ");
		
		int numPlayers = IO.readInt();
		double dealerBankroll = 10000;
		
		//Error Check for amount of players
		
		while(numPlayers < 1 || numPlayers > 6){
			System.out.println("Invalid amount of players. Please enter again: ");
			numPlayers = IO.readInt();
		}
		System.out.println("Each player and the dealer have begin with $10000");
		
		//Arrays for players, wins, and losses for money bet
		Player[][] players = new Player[numPlayers][2]; //2-D array for splitting
		double[][] betPerPlayer = new double[numPlayers][2];
		double dealerWon = 0;
		double dealerLoss = 0;
		boolean [][] hasDoubleDown = new boolean[numPlayers][2];  //keeps track of who double downed or not
		double[][] moneyLost = new double[numPlayers][2];
		double[][] moneyWon = new double[numPlayers][2];
		double[][] insuranceBet = new double[numPlayers][2];      //keeps track of who paid insurance
		boolean[][] hasInsurance = new boolean[numPlayers][2];
		
		//main loop
		while(true){
		
			for(int i = 0; i < betPerPlayer.length; i++){ //loop to fill money bet arrays
			System.out.println("Player " + (i+1) + " , enter the money you wish to bet: ");
			System.out.println("\nHint: Don't bet too much if you're a new player\n");
			double moneyBet = IO.readDouble();
			
			while(moneyBet < 0){
				System.out.println("Invalid amount of money bet. Please enter a valid amount: ");
				moneyBet = IO.readDouble();
			}
			
			betPerPlayer[i][0] = moneyBet;
			betPerPlayer[i][1] = moneyBet;  //second hand bet if there is a split is equal to the first
			
		}
		
		
		
		System.out.println("We will begin playing now: ");
		
		
		
		
			Deck playingCards = new Deck();
			playingCards.shuffle();
			
			System.out.println("Dealer will now deal cards to each player. Both will be face up. \nRemember, you are playing against the dealer, not the other players. ");
			
			for(int i = 0; i < players.length; i++){ //if player draws an ace allows them to choose the value of 1 or 11
				Card A = playingCards.deal();
				if(A.getFace() == 1){
					System.out.println("You drew an ace!");
					System.out.println("Do you want the value of the ace to be 11? If yes, type 11, or it will automatically be 1 if you type any other number: ");
					int face = IO.readInt();
					if(face == 11){
						A.setValueOfAce();
					}
				}
				Card B = playingCards.deal();
				if(B.getFace() == 1){
					System.out.println("You drew an ace!");
					System.out.println("\nIf you have a high face for your first card choose 11, if you have two aces choose 1");
					System.out.println("Do you want the value of the ace to be 11? If yes, type 11, or it will automatically be 1 if you type any other number: ");
					int face = IO.readInt();
					if(face == 11){
						B.setValueOfAce();
					}
					
				}
				
				players[i][0] = new Player(betPerPlayer[i][0], A, B); //creates a player object and puts it in the players array
				
				System.out.println("Cards for player " + (i+1) + "\nCard 1:  \nSuit: " + A.getSuit() + "\nFace: " + A.getFace() + "\nValue: " + A.getValue());
				System.out.println("---------------------------------------------------------------------------------");
				System.out.println("Card 2: \nSuit: " + B.getSuit() + "\nFace: " + B.getFace() + "\nValue: " + B.getValue());
				System.out.println("---------------------------------------------------------------------------------");
				System.out.println("Player " + (i+1) + " Score: " + players[i][0].getScore());
				System.out.println("---------------------------------------------------------------------------------");
				
				int score = players[i][0].getScore();
				
				if(score < 12){
					System.out.println("There is a 0% chance to bust. You should choose to hit at all times.");
					
				}
				else if(score == 12){
					System.out.println("There is a 31% chance to bust. You should hit.");
				}
				else if(score == 13){
					System.out.println("There is a 39% chance to bust. You should hit.");
				}
				else if(score == 14)
					System.out.println("There is a 56% chance to bust. You should hit.");
				
				else if(score == 15)
					System.out.println("There is a 58% chance to bust. You should hit if a lot of high cards were taken.");
				
				else if(score == 16)
					System.out.println("There is a 62% chance to bust. If a lot of high cards were dealt, you should hit.");
				
				else if(score == 17)
					System.out.println("There is a 69% chance to bust. If a lot of high cards were dealt, you should hit.");
				
				else if(score == 18)
					System.out.println("There is a 77% chance to bust. You should stand if most of the aces, two's and three's are taken.");
				
				else if(score == 19)
					System.out.println("There is a 85% chance to bust. You should stand if most of the aces and two's are dealt already.");
				
				else if(score == 20)
					System.out.println("There is a 92% chance to bust. You should stand, especially if all the aces were taken.");
				
				else if(score == 21)
					System.out.println("You will win as long as the dealer does not get 21!");
				
					
				if(A.getFace() == B.getFace()){  //if there is an occurrence of a split
					System.out.println("Player " + (i+1) + " , would you like to split? Enter Y or N: ");
					System.out.println("A split can be used if two cards have the same face value. If you choose to split, then you will have two hands\n"
							+ ",each dealt a new card. Both hands have the same money wagered on them and are counted seperately.");
					System.out.println("You should always split if you have an ace and 8. Pairs of 4, 5, or 10 should not \nbe split because you have a high"
							+ "of winning without splitting. 9's should only be split if high cards were given out already \nto each player. 7's should be split "
							+ "also for the same reason. You shouldn't split 6's, as theres only a 31% chance to bust with \n12. For the same reason, 2's and three's should not be split either.");
					String hasSplit = IO.readString();
					hasSplit = hasSplit.toLowerCase();
					
					while((hasSplit.equals("y") || hasSplit.equals("n")) == false){  //error check
						System.out.println("Ivalid input, please enter Y or N: ");
						hasSplit = IO.readString();
					}
					
					if(hasSplit.equals("y")){
			
						System.out.println("Player " + (i+1) + " now has two hands");
						
						Card splitCard = playingCards.deal();
						if(splitCard.getFace() == 1){
							System.out.println("You drew an ace!");
							System.out.println("Do you want the value of the ace to be 11? If yes, type 11, or it will automatically be 1 if you type any other number: ");
							int face = IO.readInt();
							if(face == 11){
								splitCard.setValueOfAce();
							}
						}
						
						//when split, two separate hands are created that are played against the dealer
						System.out.println("Split Card for Player " + (i+1) + "\nSuit: " + splitCard.getSuit() + "\nFace: " + splitCard.getFace() + "\nValue: " + splitCard.getValue());
						
						players[i][0] = new Player(betPerPlayer[i][0], A, splitCard);
						
						System.out.println("Player " + (i+1) + " hand 1 score: " + players[i][0].getScore());
						
						Card secondSplitCard = playingCards.deal();
						
						if(secondSplitCard.getFace() == 1){
							System.out.println("You drew an ace!");
							System.out.println("Do you want the value of the ace to be 11? If yes, type 11, or it will automatically be 1 if you type any other number: ");
							int face = IO.readInt();
							if(face == 11){
								secondSplitCard.setValueOfAce();
							}
						}
						
						System.out.println("Split Card for second hand for Player " + (i+1) + "\nSuit: " + secondSplitCard.getSuit() + "\nFace: " + secondSplitCard.getFace() + "\nValue: " + secondSplitCard.getValue());
						
						players[i][1] = new Player(betPerPlayer[i][0], B, secondSplitCard);
						
						System.out.println("Player " + (i+1) + " hand 2 score: " + players[i][1].getScore());
						
						int score1 = players[i][1].getScore();
						if(score1 < 12){
							System.out.println("There is a 0% chance to bust. You should choose to hit at all times.");
							
						}
						else if(score1 == 12){
							System.out.println("There is a 31% chance to bust. You should hit.");
						}
						else if(score1 == 13){
							System.out.println("There is a 39% chance to bust. You should hit.");
						}
						else if(score1 == 14)
							System.out.println("There is a 56% chance to bust. You should hit.");
						
						else if(score == 15)
							System.out.println("There is a 58% chance to bust. You should hit if a lot of high cards were taken.");
						
						else if(score1 == 16)
							System.out.println("There is a 62% chance to bust. If a lot of high cards were dealt, you should hit.");
						
						else if(score1 == 17)
							System.out.println("There is a 69% chance to bust. If a lot of high cards were dealt, you should hit.");
						
						else if(score == 18)
							System.out.println("There is a 77% chance to bust. You should stand if most of the aces, two's and three's are taken.");
						
						else if(score1 == 19)
							System.out.println("There is a 85% chance to bust. You should stand if most of the aces and two's are dealt already.");
						
						else if(score1 == 20)
							System.out.println("There is a 92% chance to bust. You should stand, especially if all the aces were taken.");
						
						else if(score1 == 21)
							System.out.println("You will win as long as the dealer does not get 21!");
						
								
					}
		
				}
				
			}
			
			
			//dealers cards, one face up and one face down
			Card dealerCardShown = playingCards.deal();
			
			//if dealer draws an ace, asked for insurance
			
			if(dealerCardShown.getFace() == 1){
				
				for(int i = 0; i < players.length; i++){
					for(int j = 0; j < 2; j++){
						
						if(players[i][j] != null){ //if a person did not split, the value of players[i][1] will be null
							
								System.out.println("Player " + (i+1) + " do you want insurance for the ace? Enter Y or N: ");
								System.out.println("Insurance should only be used if a ot of high cards were already dealt. If the dealer drew an ace, this"
										+ " \nmeans if he gets 21, you will not lose any moeny. However, if the dealer wins and does not get 21, you lose half the money"
										+ " \nyou bet, plus the money you bet.");
								
								String insurance = IO.readString();
								insurance = insurance.toLowerCase();
						
								while((insurance.equals("y") || insurance.equals("n")) == false){
									System.out.println("Ivalid input, please enter y or n: ");
									insurance = IO.readString();
								}
						
								if(insurance.equals("y")){
									
									insuranceBet[i][j] = 0.5 * betPerPlayer[i][j]; //half your money bet is put into insurace
									hasInsurance[i][j] = true;
									moneyLost[i][j] += insuranceBet[i][j];
								}
								
							}
						}	
					}
			}
				
			Card dealerHidden = playingCards.deal();
			int dealerScore = dealerCardShown.getValue() + dealerHidden.getValue();
			System.out.println("Card for dealer " + "\nSuit: " + dealerCardShown.getSuit() + " \nFace: " + dealerCardShown.getFace() + "\nValue: " + dealerCardShown.getValue());
			System.out.println("---------------------------------------------------------------------------------");
			
			for(int i = 0; i < betPerPlayer.length; i++){ //loop to ask whether each player would like to double down
				System.out.println("Player " + (i+1) + " , would you like to double down? You will have to stand after. Enter Y or N: ");
				System.out.println("\nDoubling down will increase your wager by 100%. However, you will be dealt exactly one card.");
				System.out.println("Hint: Always double down on values of 11, the higher your score is, \nthe higher the chances of busting. You should not double down"
						+ " when your score is less than 9, as there is a \nhigher chance of ending up with a low score.");
				String doubleDown = IO.readString();
				doubleDown = doubleDown.toLowerCase();
				
				while((doubleDown.equals("y") || doubleDown.equals("n")) == false){
					System.out.println("Ivalid input, please enter y or n: ");
					doubleDown = IO.readString();
				}
				
				if(doubleDown.equals("y")){
					
					double betMoney = players[i][0].getMoneyBet();
					betPerPlayer[i][0] = 2 * betMoney;
					hasDoubleDown[i][0] = true;
				}
				else{
					
					hasDoubleDown[i][0] = false;
				}
				if(players[i][1] != null){ // ask to double down second hand if there is an occurrence of a split
					System.out.println("Player " + (i+1) + " , would you like to double down on your second hand? Enter y or n: ");
					String doubleDown2 = IO.readString();
					doubleDown2 = doubleDown2.toLowerCase();
					
					while((doubleDown2.equals("y") || doubleDown2.equals("n")) == false){
						System.out.println("Ivalid input, please enter y or n: ");
						doubleDown2 = IO.readString();
					}
					
					if(doubleDown2.equals("y")){
			
						double betMoney = players[i][1].getMoneyBet();
						betMoney = 2 * betMoney;
						betPerPlayer[i][1] = betMoney;
						hasDoubleDown[i][1] = true;
					}
					else{
						
						hasDoubleDown[i][1] = false;
					}
				}
			}
			
			
			for(int i = 0; i < players.length; i++){
				for(int j = 0; j < 2; j++){
					
					if(players[i][j] != null){//check for split
					
						if(hasDoubleDown[i][j]){
						Card C = playingCards.deal();
						
						if(C.getFace() == 1){
							System.out.println("You drew an ace!");
							System.out.println("Do you want the value of the ace to be 11? If yes, type 11, or it will automatically be 1 if you type any other number: ");
							int face = IO.readInt();
							if(face == 11){
								C.setValueOfAce();
							}
					
						}
				
						//checks player score after each draw to see whether they bust or not
						System.out.println("New card for player " + (i+1) + "\nSuit: " + C.getSuit() + " \nFace: " + C.getFace() + "\nValue: " + C.getValue());
						System.out.println("---------------------------------------------------------------------------------");
				
						int score = players[i][j].getScore() + C.getValue();

						players[i][j].setScore(score);
				
						System.out.println("Your score is: " + score);
						
				
						if(score > 21){ 
							System.out.println("Bust!");
							System.out.println("---------------------------------------------------------------------------------");
							moneyLost[i][j] += (betPerPlayer[i][j]);
							dealerWon += (betPerPlayer[i][j]);
						}
						else if(score == 21){
							System.out.println("You move on!");
							System.out.println("---------------------------------------------------------------------------------");
						}
						else
							continue;
					
						}
					
					
					else{//hit or stand for each player
							System.out.println("Player " + (i+1) + ", would you like to hit or stand? Enter hit or stand: ");
						
							String decision = IO.readString();
							decision = decision.toLowerCase();
				
							while(((decision.equals("hit")) || (decision.equals("stand"))) == false){ //error checking for hit and stand
								System.out.println("Invalid response. Please enter either hit or stand: ");
								decision = IO.readString();
							}
				
							while(decision.equals("hit")){
					
								Card C = playingCards.deal();
					
								if(C.getFace() == 1){
									System.out.println("You drew an ace!");
									System.out.println("Do you want the value of the ace to be 11? If yes, type 11, or it will automatically be 1 if you type any other number: ");
									int face = IO.readInt();
									if(face == 11){
										C.setValueOfAce();
									}
						
								}
					
							//checks player score after each draw to see whether they bust or not
								System.out.println("New card for player " + (i+1) + "\nSuit: " + C.getSuit() + " \nFace: " + C.getFace() + "\nValue: " + C.getValue());
								System.out.println("---------------------------------------------------------------------------------");
					
								int score = players[i][j].getScore() + C.getValue();
								players[i][j].setScore(score);
					
								System.out.println("Your score is: " + score);
								if(score < 12){
									System.out.println("There is a 0% chance to bust. You should choose to hit at all times.");
									
								}
								else if(score == 12){
									System.out.println("There is a 31% chance to bust. You should hit.");
								}
								else if(score == 13){
									System.out.println("There is a 39% chance to bust. You should hit.");
								}
								else if(score == 14)
									System.out.println("There is a 56% chance to bust. You should hit.");
								
								else if(score == 15)
									System.out.println("There is a 58% chance to bust. You should hit if a lot of high cards were taken.");
								
								else if(score == 16)
									System.out.println("There is a 62% chance to bust. If a lot of high cards were dealt, you should hit.");
								
								else if(score == 17)
									System.out.println("There is a 69% chance to bust. If a lot of high cards were dealt, you should hit.");
								
								else if(score == 18)
									System.out.println("There is a 77% chance to bust. You should stand if most of the aces, two's and three's are taken.");
								
								else if(score == 19)
									System.out.println("There is a 85% chance to bust. You should stand if most of the aces and two's are dealt already.");
								
								else if(score == 20)
									System.out.println("There is a 92% chance to bust. You should stand, especially if all the aces were taken.");
								
								else if(score == 21)
									System.out.println("You will win as long as the dealer does not get 21!");
					
								if(score > 21){ 
									System.out.println("Bust!");
									System.out.println("---------------------------------------------------------------------------------");
									moneyLost[i][j] += (betPerPlayer[i][j]);
									dealerWon += (betPerPlayer[i][j]);
									break;
								}
								else if(score == 21){
									System.out.println("You move on!");
									System.out.println("---------------------------------------------------------------------------------");
									break;
								}
								else{
									System.out.println("Hit or stand?: ");
									decision = IO.readString();
									decision = decision.toLowerCase();
									while(((decision.equals("hit")) || (decision.equals("stand"))) == false){
										System.out.println("Invalid response. Please enter either hit or stand: ");
										decision = IO.readString();
										}
									}
						
								}			
							
							
						}
				
				}}
			}
				
			System.out.println("Dealer card that was face down is: " + "\nSuit: " + dealerHidden.getSuit() + "\nFace: " + dealerHidden.getFace());
			System.out.println("---------------------------------------------------------------------------------");	
			System.out.println("Dealer score is: " + dealerScore);
			System.out.println("---------------------------------------------------------------------------------");
			
			while(dealerScore < 17){ //dealer will continue to draw cards until his total is 17 or higher
					Card newDeal = playingCards.deal();
					System.out.println("Dealt card is: " + "\nSuit: " + newDeal.getSuit() + "\nFace: " + newDeal.getFace() + "\nValue: " + newDeal.getValue());
					System.out.println("---------------------------------------------------------------------------------");
					System.out.println("Dealer Score = " + (dealerScore + newDeal.getValue()));
					System.out.println("---------------------------------------------------------------------------------");
					dealerScore = dealerScore + newDeal.getValue();
					System.out.println("---------------------------------------------------------------------------------");
				}
			
				if(dealerScore == 21){
					
					System.out.println("If you have insurance you get double the amount you paid for insurance");
					
					for(int i =0; i < players.length; i++){
						for(int j = 0; j < 2; j++){
							
							if(players[i][j] != null){
								moneyWon[i][j] += (2*insuranceBet[i][j]);
							}
						}
					}
					
					
				}
				
				if(dealerScore > 21){ //only players who have not busted will win money by the .moveOn() method in the Player class
					System.out.println("Dealer bust!");
					for(int i = 0; i < players.length; i++){
						for(int j = 0; j < 2; j++){
							if(players[i][j] != null){
								if(players[i][j].moveOn() == true){
									moneyWon[i][j] += (betPerPlayer[i][j]);
									dealerLoss += (betPerPlayer[i][j]);
								}
							}
						}
					}
				}
				else{// checks if a player did not bust
					for(int i = 0; i < players.length; i++){
						for(int j = 0; j < 2; j++){
							if(players[i][j] != null){
								if((players[i][j].getScore() < dealerScore) && (players[i][j].moveOn() == true)){//compares dealer score to player
									System.out.println("Player " +  (i+1) + " lost!");
									moneyLost[i][j] += (betPerPlayer[i][j]);
									dealerWon += (betPerPlayer[i][j]);
								}
								else if((players[i][j].getScore() > dealerScore) && players[i][j].moveOn() == true){
									System.out.println("Player " + (i+1) + " won!");
									moneyWon[i][j] += (betPerPlayer[i][j]);
									dealerLoss += (betPerPlayer[i][j]);
								}
								else if((players[i][j].getScore() == dealerScore) && (players[i][j].moveOn() == true)){
									System.out.println("Push!");
								}
							}
						}
					}
				}
				
				double dealerTotal = dealerBankroll + (dealerWon - dealerLoss); //calculation for total money won and lost after each round
				System.out.println("Dealer total money at the end of this round: $" + dealerTotal);
				
				for(int i = 0; i < players.length; i++){//displays the total winnings and losses after each round
					double playerTotal = players[i][0].getMoney() + (moneyWon[i][0] - moneyLost[i][0] + moneyWon[i][1] - moneyLost[i][1]);
					System.out.println("Player " + (i+1) + " total at the end of this round: $" + playerTotal);
				}
				System.out.println("If you have a negative total, you owe the dealer that amount!");
				System.out.println("Would you like to play again? Enter 1 for yes or -1 for no: ");
				int play = IO.readInt();
				
				if(play == -1){ //if they do not want to play again the main loop will break
					break;
				}
			
		}
	}

}
