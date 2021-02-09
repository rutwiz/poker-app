package pokerApp;

public class Utils {
	
	public static void clearScreen() {  
		System.out.println(System.lineSeparator().repeat(40));
		
//		System.out.print("\r\n"
//				+ ".------..------..------..------..------.\r\n"
//				+ "|P.--. ||O.--. ||K.--. ||E.--. ||R.--. |\r\n"
//				+ "| :/\\: || :/\\: || :/\\: || (\\/) || :(): |\r\n"
//				+ "| (__) || :\\/: || :\\/: || :\\/: || ()() |\r\n"
//				+ "| '--'P|| '--'O|| '--'K|| '--'E|| '--'R|\r\n"
//				+ "`------'`------'`------'`------'`------'\r\n"
//				+ "\r\n\r\n\r\n");
		
		System.out.print("\r\n"
				+ " /$$$$$$$           /$$                          \r\n"
				+ "| $$__  $$         | $$                          \r\n"
				+ "| $$  \\ $$ /$$$$$$ | $$   /$$  /$$$$$$   /$$$$$$ \r\n"
				+ "| $$$$$$$//$$__  $$| $$  /$$/ /$$__  $$ /$$__  $$\r\n"
				+ "| $$____/| $$  \\ $$| $$$$$$/ | $$$$$$$$| $$  \\__/\r\n"
				+ "| $$     | $$  | $$| $$_  $$ | $$_____/| $$      \r\n"
				+ "| $$     |  $$$$$$/| $$ \\  $$|  $$$$$$$| $$      \r\n"
				+ "|__/      \\______/ |__/  \\__/ \\_______/|__/      \r\n"
				+ "\r\n\r\n");
	}
	
	static void showStats()
	{
		System.out.println("No. | Name | BuyIn | Current Stack");
		for(int i = 0; i < Gameplay.numberOfPlayers; i++)
		{
			System.out.println((i+1) + ".   " + Gameplay.playersInGame.get(i).name + "  $" + Gameplay.playersInGame.get(i).buyIn + "      $" + Gameplay.playersInGame.get(i).stackSize);
		}
		
		System.out.print("\n\n");
	}
	
	static void showPreFlop()
	{
		System.out.println("X X X X X");
	}
	
	static void showFlop()
	{
		for (int i = 0; i < 3; i++) {
	    	Gameplay.communityCards[i].displayCard();
	    }
		System.out.println("X X");
	}
	
	static void showTurn()
	{
		for (int i = 0; i < 4; i++) {
	    	Gameplay.communityCards[i].displayCard();
	    }
		System.out.println("X");
	}
	
	static void showRiver()
	{
		for (int i = 0; i < 5; i++) {
	    	Gameplay.communityCards[i].displayCard();
	    }
		System.out.println("");
	}
	
	static void resetFolds()
	{
		for(int i = 0; i < Gameplay.numberOfPlayers; i++) Gameplay.playersInGame.get(i).fold = false;
	}
	
	static void resetBets()
	{
		for(int i = 0; i < Gameplay.numberOfPlayers; i++) Gameplay.playersInGame.get(i).betInRound = 0;
	}
	
	static boolean isBetsEqual()
	{
        int bet = Gameplay.playersInGame.get(0).betInRound;
		for(int i = 1; i < Gameplay.numberOfPlayers; i++)
        {
            if(Gameplay.playersInGame.get(i).betInRound != bet && !Gameplay.playersInGame.get(i).fold) return false;
        }
		return true;
	}

    static int activePlayers()
	{
        int count = 0;
		for(int i = 0; i < Gameplay.numberOfPlayers; i++) if(!Gameplay.playersInGame.get(i).fold) count++;
        return count;
	}
	
}