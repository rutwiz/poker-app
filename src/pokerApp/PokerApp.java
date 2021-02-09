package pokerApp;
import java.util.*;

public class PokerApp {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		Utils.clearScreen();
		
		while(true)
		{	
			System.out.println("1. New Game");
			System.out.println("2. Exit");
			System.out.print("Enter Option: ");
			
			int option = scan.nextInt();
			
			switch(option)
			{
				default:
					Utils.clearScreen();
					System.out.println("Enter Valid Choice.\n");
					break;
					
				case 1:
					Utils.clearScreen();
					System.out.print("Enter Number of Players: ");
					
					int numberPlayers = scan.nextInt();
					
					Gameplay.setNumOfPlayer(numberPlayers);
					Gameplay.startGame();
					
					break;
					
				case 2:
					scan.close();
					System.out.println(System.lineSeparator().repeat(40));
					System.exit(0);	
			}
		}
			
	}
	
	
}
