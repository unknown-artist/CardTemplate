import java.util.ArrayList;
import java.util.Random;

public class Shoe {

    private final String[] SUITS = {"Clubs", "Spades","Diamonds", "Hearts"};
    private final String[] SUITS_UNICODE = {"\u2663", "\u2660", "\u2666", "\u2665"};
    private final String[] COLORS ={"Black", "Black", "Red", "Red"};
    private final String[] RANKS = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
    private final String[] RANKS_STRING = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private final int[] VALUE = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    private int deckCount;
    public ArrayList<Card> shoeCards;

    public Shoe(int deckCount, int jokerCount){
        shoeCards = new ArrayList<Card>();
        this.deckCount = deckCount;

        for( int i = 0; i < deckCount; i++ ){
            shoeCards.addAll(generateDeck());
        }
        
        for( int i = 0; i < jokerCount; i++){
            shoeCards.add( new Card("","","Joker", 0, "", ""));
        }
    }

    public  ArrayList<Card > generateDeck(){
        ArrayList<Card> shoeCards = new ArrayList<Card>();
        for( int i = 0; i < SUITS.length; i++){
            for( int j = 0; j < RANKS.length;j++){
                shoeCards.add(new Card(COLORS[i], SUITS[i], RANKS[j], VALUE[j], SUITS_UNICODE[i], RANKS_STRING[j]));
            }
        }
        return shoeCards;
    }

    public  Card randomCard(){
        Random rand = new Random();

        int randomIndex = rand.nextInt(shoeCards.size());
        Card randomCard = shoeCards.get(randomIndex);
        shoeCards.remove(randomIndex);

        return randomCard;
    }

    public ArrayList<Card> randomNCard(int n){
        ArrayList<Card> randomCard = new ArrayList<Card>();

        for( int i = 0; i < n; i++){
            randomCard.add(randomCard());
        }

        return randomCard;
    }

    public void addCard(Card card){
        shoeCards.add(card);
    }

    public void removeCard(Card card){
        shoeCards.remove(card);
    }
}
