import java.util.*;

class Poker{

	int numberOfHandCards;
	int numberOfTableCards;
	int numberOfHandCardsToSelect;
	int numberOfTableCardsToSelect;
	Shoe shoe;
	ArrayList< Card > handCards; 
	ArrayList< Card > tableCards;
	ArrayList< String > pokerHandList;

	public Poker(int numberOfHandCards, int numberOfTableCards, int numberOfHandCardsToSelect, int numberOfTableCardsToSelect, Shoe shoe){
		this.numberOfHandCards = numberOfHandCards;
		this.numberOfTableCards = numberOfTableCards;
		this.numberOfHandCardsToSelect = numberOfHandCardsToSelect;
		this.numberOfTableCardsToSelect = numberOfTableCardsToSelect;
		this.shoe = shoe;
		handCards = shoe.randomNCard(numberOfHandCards);
		tableCards = shoe.randomNCard(numberOfTableCards);
		initializePokerHandList();
	}

	private void initializePokerHandList(){
		pokerHandList = new ArrayList<String>();
		pokerHandList.add("5 of a Kind");
		pokerHandList.add("Straight Flush");
		pokerHandList.add("4 of a Kind");
		pokerHandList.add("Full House");
		pokerHandList.add("Flush");
		pokerHandList.add("Straight");
		pokerHandList.add("3 of a Kind");
		pokerHandList.add("2 Pair");
		pokerHandList.add("1 Pair");
		pokerHandList.add("High Card");
		pokerHandList.add("Baba Ji Ka Thullu");
	}

	private HashMap< String, Integer > getRankFrequency(ArrayList<Card> cards){
		HashMap< String, Integer > rankFrequency = new HashMap< String, Integer >();

		for(Card card : cards){
			if(rankFrequency.containsKey(card.rank))
				rankFrequency.put(card.rank, rankFrequency.get(card.rank) + 1);
			else
				rankFrequency.put(card.rank, 1);
		}

		return rankFrequency;
	}

	private boolean is5OfAKind(ArrayList<Card> cards){ //ArrayList of size 5
		HashMap< String, Integer > rankFrequency = getRankFrequency(cards);

		return rankFrequency.containsValue(5);
	}

	private boolean isStraightFlush(ArrayList<Card> cards){	//ArrayList of size 5
		return (isStraight(cards) && isFlush(cards));
	}

	private boolean is4OfAKind(ArrayList<Card> cards){ //ArrayList of size 5
		HashMap< String, Integer > rankFrequency = getRankFrequency(cards);

		return rankFrequency.containsValue(4);
	}

	private boolean isFullHouse(ArrayList<Card> cards){	//ArrayList of size 5
		HashMap< String, Integer > rankFrequency = getRankFrequency(cards);
		
		return (rankFrequency.containsValue(3) && rankFrequency.containsValue(2));
	}

	private boolean isFlush(ArrayList<Card> cards){ //ArrayList of size 5
		HashMap< String, Integer > suitFrequency = new HashMap< String, Integer >();

		for(Card card : cards){
			if(suitFrequency.containsKey(card.suit))
				suitFrequency.put(card.suit, suitFrequency.get(card.suit) + 1);
			else
				suitFrequency.put(card.suit, 1);
		}

		return suitFrequency.containsValue(5);
	}

	private boolean isStraight(ArrayList<Card> cards){	//ArrayList of size 5
		int[] sequence = new int[15];

		for(Card card : cards){
			sequence[card.value] = 1;
			if((card.rank).compareTo("Ace") == 0)
				sequence[14] = 1;
		}

		int consecutiveCount = sequence[1] + sequence[2] + sequence[3] + sequence[4];
		for(int i = 5; i <= 14; i++){
			consecutiveCount += sequence[i];
			consecutiveCount -= sequence[i - 5];
			if(consecutiveCount == 5) return true;
		}

		return false;
	}

	private boolean has3OfAKind(ArrayList<Card> cards){ //ArrayList of size 5
		HashMap< String, Integer > rankFrequency = getRankFrequency(cards);

		return rankFrequency.containsValue(3);
	}

	private boolean has2Pair(ArrayList<Card> cards){ //ArrayList of size 5
		HashMap< String, Integer > rankFrequency = getRankFrequency(cards);

		int countPairs = 0;
		for(int freq : rankFrequency.values())
			if(freq == 2)countPairs++;

		return (countPairs == 2); 
	}


	private boolean has1Pair(ArrayList<Card> cards){ //ArrayList of size 5
		HashMap< String, Integer > rankFrequency = getRankFrequency(cards);

		return rankFrequency.containsValue(2);
	}	

	private Card getHighCard(ArrayList<Card> cards){ //ArrayList of variable size
		Card highCard = cards.get(0);

		for(Card card : cards){
			if((card.rank).compareTo("Ace") == 0) return card;
			highCard = (card.value > highCard.value) ? card : highCard;
		}

		return highCard;
	}

	private ArrayList< ArrayList<Card> > generateCombinations(ArrayList< Card > cards, int n){
		ArrayList< ArrayList< Card > > combinations = new ArrayList< ArrayList< Card > >();

		for(int i = 0; i < (1 << cards.size()); i++){
			int cnt = 0;
			for(int j = 0; j < cards.size(); j++) cnt += ((i & (1 << j)) == 0) ? 0 : 1;
			if(cnt != n) continue;
			ArrayList< Card > newCombination = new ArrayList< Card >();
			for(int j = 0; j < cards.size(); j++)
				if((i & (1 << j)) != 0) newCombination.add(cards.get(j));
			combinations.add(newCombination);
		}
		return combinations;
	}

	public void display(ArrayList< Card > cards){
		for(Card card : cards)
			// System.out.print(card.suit + " " + card.rank + "\t\t");
			System.out.print(card + "\t");
		System.out.println();
	}


	public void computeBestHand(){

		ArrayList< ArrayList<Card> > combinationsOfHandCards = generateCombinations(handCards, numberOfHandCardsToSelect);
		//arraylist of cards containing all combinations of size exactly = numberOfHandCardsToSelect from handCards;

		ArrayList< ArrayList<Card> > combinationsOfTableCards = generateCombinations(tableCards, numberOfTableCardsToSelect);
		//arraylist of cards containing all combinations of size exactly = numberOfTableCardsToSelect from tableCards;

		ArrayList< Card > bestHandCards = new ArrayList< Card >();
		String bestHand = "Baba Ji Ka Thullu";

		for(ArrayList<Card> hc : combinationsOfHandCards){

			for(ArrayList<Card> tc : combinationsOfTableCards){

				ArrayList<Card> cards = new ArrayList<Card>(); //arraylist of cards containing exactly (numberOfHandCardsToSelect + numberOfTableCardsToSelect) cards
				String hand = "High Card";
				cards.addAll(hc);
				cards.addAll(tc);
				// display(cards);

				if(is5OfAKind(cards)) hand = "5 of a Kind";
				else if(isStraightFlush(cards)) hand = "Straight Flush";
				else if(is4OfAKind(cards)) hand = "4 of a Kind";
				else if(isFullHouse(cards)) hand = "Full House";
				else if(isFlush(cards)) hand = "Flush";
				else if(isStraight(cards)) hand = "Straight";
				else if(has3OfAKind(cards)) hand = "3 of a Kind";
				else if(has2Pair(cards)) hand = "2 Pair";
				else if(has1Pair(cards)) hand = "1 Pair";

				// System.out.print(hand + "\t\t\t");
				// display(cards);

				if(pokerHandList.indexOf(hand) < pokerHandList.indexOf(bestHand) || (pokerHandList.indexOf(hand) == pokerHandList.indexOf(bestHand) && getHighCard(cards).compareValue(getHighCard(bestHandCards)) > 0)){
					bestHand = hand;
					bestHandCards = cards;
				}
			}
		}

		System.out.println(bestHand + "\t\t");
		display(bestHandCards);

	}
}