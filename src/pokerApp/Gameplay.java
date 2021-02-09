package pokerApp;

import java.util.*;

public class Gameplay {
	
	static int pot = 0, sb = 0, bb = 1;
	static int numberOfPlayers;
	static ArrayList<Player> playersInGame = new ArrayList<Player>();
	static Card[] communityCards = new Card[5];
    static int bigBlind = 2, smallBlind = 1;
	
	static Scanner scan = new Scanner(System.in);
	
	static void startGame()
	{
		initializePlayers();
		Utils.clearScreen();
		
		boolean exit = false;
		while(!exit)
		{
			System.out.println("1. Start New Round");
			System.out.println("2. View Stats");
			System.out.println("3. Buy In");
			System.out.println("4. Back");
			
			int option = scan.nextInt();
			
			switch(option)
			{
				default:
					Utils.clearScreen();
					System.out.println("Enter a Valid Choice.\n");
					break;
					
				case 1:
					startRound();
					break;
					
				case 2:
					Utils.clearScreen();
					Utils.showStats();
					break;
					
				case 3:
					extraBuyIn();
					break;
					
				case 4:
					Utils.clearScreen();
					exit = true;
					break;
			}
		}
	}
	
	static void setNumOfPlayer(int n)
	{
		Gameplay.numberOfPlayers = n;
	}
	
	static void initializePlayers()
	{
		
		for(int i = 0; i < numberOfPlayers; i++)
		{
			String name;
			int buyIn;
			
			Utils.clearScreen();
			
			System.out.print("Enter Player " + (i+1) + " name: ");
			name = scan.next();
			System.out.print("Enter Player " + (i+1) + " Buyin: ");
			buyIn = scan.nextInt();
			
			playersInGame.add(new Player(name, buyIn));
		}
		
	}
	
	
	
	static void dealCards()
	{
		Integer[] arr = new Integer[52];
		
	    for (int i = 0; i < arr.length; i++) {
	        arr[i] = i;
	    }
	    
	    Collections.shuffle(Arrays.asList(arr));
	    
	    for (int i = 0; i < 5; i++) {
	    	//System.out.println("Setting Comm Card: " + arr[i]);
	    	communityCards[i] = new Card();
	    	communityCards[i].setCard(arr[i]);
	    }
	    
	    for (int i = 5, currPlayer = 0; i < 2*numberOfPlayers + 5; i+=2, currPlayer++) {
	    	Card temp = new Card(), temp2 = new Card();
	    	//System.out.println("Setting Player " + currPlayer + " Card: " + arr[i]);
	    	//System.out.println("Setting Player " + currPlayer + " Card: " + arr[i+1]);
	    	temp.setCard(arr[i]);
	    	temp2.setCard(arr[i+1]);
	    	
	    	playersInGame.get(currPlayer).setCards(temp, temp2);
	    }
	}
	
	static void extraBuyIn()
	{
		Utils.clearScreen();
		
		System.out.print("Enter Player Number: ");
		int n_player = scan.nextInt();
		System.out.print("Enter BuyIn Amount: ");
		int n_buyIn = scan.nextInt();
		
		if(n_player > numberOfPlayers || n_player < 1)
		{
			System.out.println("Invalid Player ID.\n");
			return;
		}
		
		if(n_buyIn < 0)
		{
			System.out.println("Invalid BuyIn Amount.\n");
			return;
		}
		
		playersInGame.get(n_player-1).buyIn(n_buyIn);
		
		System.out.println("Bought in " + n_buyIn + " for " + playersInGame.get(n_player-1).name + "\n");
	}
	
	static void startRound() {
		
		// sitting out implement karna hai
		
		sb = (sb+1)%numberOfPlayers; 
		bb = (bb+1)%numberOfPlayers;
		
		pot = 0;
		
		dealCards();
		Utils.resetFolds();
		
		playRound(0);
        Utils.resetBets();
		Utils.clearScreen();
		
		playRound(1);
        Utils.resetBets();
		Utils.clearScreen();
		
		playRound(2);
        Utils.resetBets();
		Utils.clearScreen();
		
		playRound(3);
        Utils.resetBets();
		Utils.clearScreen();
		
	}
	
	static void playRound(int r_num)
	{
		int turn, currentBet, playersPlayed = 0;
        int currentPot = 0;
		
		if(r_num == 0)
        {
            turn = (bb+1)%numberOfPlayers;
            currentBet = bigBlind;

            playersInGame.get(sb).stackSize -= smallBlind;
            playersInGame.get(bb).stackSize -= bigBlind;

            playersInGame.get(sb).betInRound = smallBlind;
            playersInGame.get(bb).betInRound = bigBlind;

            currentPot = smallBlind + bigBlind;

        }
		else
        {
            turn = sb;
            currentBet = 0;
        }
		
		while(!Utils.isBetsEqual() || (playersPlayed < numberOfPlayers))
		{
            System.out.println(playersInGame.get(turn).name + "inside at " + r_num);
            playersPlayed++;
			if(playersInGame.get(turn).fold)
            {
                turn = (turn+1)%numberOfPlayers;
                continue;
            }
				
			Utils.clearScreen();
			
			boolean exit = false;
			while(!exit)
			{
				if(r_num == 0) Utils.showPreFlop();
				else if(r_num == 1) Utils.showFlop();
				else if(r_num == 2) Utils.showTurn();
				else if(r_num == 3) Utils.showRiver();
					
				System.out.println(playersInGame.get(turn).name + "'s Turn\n");
				System.out.println("1. Call");
				System.out.println("2. Raise");
				System.out.println("3. Check");
				System.out.println("4. Fold\n");
				System.out.print("Your Cards: ");
				playersInGame.get(turn).showCards();
					
				int option = scan.nextInt();
					
				switch(option)
				{
					default:
						Utils.clearScreen();
						System.out.println("Enter a Valid Choice.\n");
						break;
							
					case 1:	
						if(currentBet - playersInGame.get(turn).betInRound >= playersInGame.get(turn).stackSize) // all in ka kaam karo
                        {
                            Utils.clearScreen();
						    System.out.println("You Have: $" + playersInGame.get(turn).stackSize + " mn");
                            System.out.println("Insufficient to call");
                        }
                        else
                        {
                            currentPot += (currentBet - playersInGame.get(turn).betInRound);
                            playersInGame.get(turn).stackSize -= (currentBet - playersInGame.get(turn).betInRound);
                            playersInGame.get(turn).betInRound = currentBet;

                            System.out.println(playersInGame.get(turn).name + " called for " + playersInGame.get(turn).betInRound);
                        }
                        exit = true;
						break;
							
						case 2:
							boolean r_exit = false;
                            Utils.clearScreen();
							while(!r_exit)
							{
								System.out.println("You Have: $" + playersInGame.get(turn).stackSize + " mn");
								System.out.println("Enter Amount of Bet: ");
								int r_bet = scan.nextInt();
								
								if(r_bet + playersInGame.get(turn).betInRound < currentBet || r_bet > playersInGame.get(turn).stackSize)
                                {
                                    Utils.clearScreen();
                                    System.out.println("Invalid Bet");
                                }
                                else
                                {
                                    currentPot += r_bet;
                                    currentBet = r_bet + playersInGame.get(turn).betInRound;
                                    playersInGame.get(turn).betInRound = currentBet;
                                    playersInGame.get(turn).stackSize -= r_bet;
                                    r_exit = true;
                                    System.out.println(playersInGame.get(turn).name + " raised for " + playersInGame.get(turn).betInRound);
                                }	
							}
							exit = true;
							break;
							
						case 3:
                            if(playersInGame.get(turn).betInRound != currentBet)
                            {
                                Utils.clearScreen();
                                System.out.println("You cannot check now haha lol xD");
                            }
							else
                            {
                                System.out.println(playersInGame.get(turn).name + " checked.");
                                exit = true;
                            }
							break;
							
						case 4:
							playersInGame.get(turn).fold = true;
							exit = true;
							break;
					}
				}
			turn = (turn+1)%numberOfPlayers;
			}
		}
	}