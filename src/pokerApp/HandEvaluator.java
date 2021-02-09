package pokerApp;

import java.util.Arrays;

public class HandEvaluator {
	
	static int isRoyalFlush(Card[] hand) {
		int[] ranks = new int[5];
				
		for(int i = 0; i < 5; i++) ranks[i] = hand[i].rank;
		Arrays.sort(ranks);
				
		if(isFlush(hand) == 5 && isStraight(hand) == 4 && ranks[0] == 1 && ranks[4] == 13) return 9;
		else return 0;
		
		}
	
	static int isStraightFlush(Card[] hand) {
		
		if(isFlush(hand) == 5 && isStraight(hand) == 4) return 8;
		else return 0;
	}

	static int isQuads(Card[] hand) {
		
		int[] ranks = new int[5];
		
		for(int i = 0; i < 5; i++) ranks[i] = hand[i].rank;
		Arrays.sort(ranks);
		
		if((ranks[0] == ranks[1] && ranks[1] == ranks[2] && ranks[2] == ranks[3]) || (ranks[1] == ranks[2] && ranks[2] == ranks[3] && ranks[3] == ranks[4])) return 7;
		
		return 0;
	}
	
	static int isFullHouse(Card[] hand) {
		int[] ranks = new int[5];
		
		for(int i = 0; i < 5; i++) ranks[i] = hand[i].rank;
		Arrays.sort(ranks);
		
		if((ranks[0] == ranks[1] && ranks[2] == ranks[3] && ranks[3] == ranks[4]) || (ranks[3] == ranks[4] && ranks[0] == ranks[1] && ranks[1] == ranks[2])) return 6;
		
		return 0;
	}
	
	static int isFlush(Card[] hand) {
		
		char suit = hand[0].suit;
		
		for(int i = 1; i < 5; i++) if(hand[i].suit != suit) return 0;
		
		return 5;
	}
	
	static int isStraight(Card[] hand) {
		
		int[] ranks = new int[5];
		
		for(int i = 0; i < 5; i++) ranks[i] = hand[i].rank;
		Arrays.sort(ranks);
		
		if(ranks[0] == 1 && ranks[4] != 5)
		{
			if(ranks[0] == 1 && ranks[1] == 10 && ranks[2] == 11 && ranks[3] == 12 && ranks[4] == 13) return 4;
			else return 0;
		}
		else
		{
			for(int i = 1; i < 5; i++) if(ranks[i]-ranks[i-1] != 1) return 0;
		}
		
		return 4;
	}
		
	static int isThreeOfAKind(Card[] hand) {
		int[] ranks = new int[5];
		
		for(int i = 0; i < 5; i++) ranks[i] = hand[i].rank;
		Arrays.sort(ranks);
		
		if((ranks[0] == ranks[1] && ranks[1] == ranks[2]) || (ranks[1] == ranks[2] && ranks[2] == ranks[3]) || (ranks[2] == ranks[3] && ranks[3] == ranks[4])) return 3;
		
		return 0;
	}
	
	static int isTwoPair(Card[] hand) {
		int[] ranks = new int[5];
		
		for(int i = 0; i < 5; i++) ranks[i] = hand[i].rank;
		Arrays.sort(ranks);
		
		if( (ranks[0] == ranks[1] && ((ranks[2] == ranks[3]) || (ranks[3] == ranks[4])))  || (ranks[1] == ranks[2] && ranks[3] == ranks[4]) ) return 2;
		
		return 0;
	}

	static int isPair(Card[] hand) {
		int[] ranks = new int[5];
		
		for(int i = 0; i < 5; i++) ranks[i] = hand[i].rank;
		Arrays.sort(ranks);
		
		if((ranks[0] == ranks[1]) || (ranks[1] == ranks[2]) || (ranks[2] == ranks[3]) || (ranks[3] == ranks[4])) return 1;
		
		return 0;
	}

}
