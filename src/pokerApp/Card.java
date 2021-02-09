package pokerApp;

// Spade(0-12), Hearts(13-25), Diamonds(26-38), Clubs(39-51)

public class Card {
	int rank;
	char suit;
	
	void setCard(int n)
	{
		this.rank = n % 13 + 1;
		int n_suit = n / 13;
		
		switch(n_suit)
		{
			case 0:
				this.suit = 's';
				break;
			case 1:
				this.suit = 'h';
				break;
			case 2:
				this.suit = 'd';
				break;
			case 3:
				this.suit = 'c';
				break;
			default:
				System.out.println("Kuch to gadbad hai.. card ke index me problem hai");
		}
	}
	
	void displayCard()
	{
		char s = 0;
		int i = 0;
		
		if(rank == 1) s = 'A';
		else if(rank == 11) s = 'J';
		else if(rank == 12) s = 'Q';
		else if(rank == 13) s = 'K';
		else i = rank;
		
		if(i == 0) System.out.printf("%c%c ", s, suit);
		else System.out.printf("%d%c ", rank, suit);
	}
}
