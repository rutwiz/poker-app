package pokerApp;

public class Player {
	
	String name = "";
	Card[] cards = new Card[2];
	
	int buyIn = 0;
	int stackSize = 0;
	int betInRound = 0;
	
	boolean fold = false;
	
	Player(String s, int n)
	{
		this.buyIn = n;
		this.name = s;
		this.stackSize = this.buyIn;
	}
	
	void setCards(Card a, Card b)
	{
		this.cards[0] = a;
		this.cards[1] = b;
	}
	
	void buyIn(int n)
	{
		this.buyIn += n;
		this.stackSize += n;
	}
	
	void showCards()
	{
		cards[0].displayCard();
		cards[1].displayCard();
	}
}
