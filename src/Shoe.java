import java.util.ArrayList;
import java.util.Random;

public class Shoe {

    private final String[][] SUITS = {{"Clubs", "Black"}, {"Spades", "Black"}, {"Diamonds", "Red"}, {"Hearts", "Red"}};
    private final String[] RANKS = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
    private int deckCount;
    public ArrayList<Card> deck;

    public Shoe(int deckCount){
        this.deckCount = deckCount;

        for( int i = 0; i < deckCount; i++ ){
            deck.addAll(generateDeck());
        }
    }

    public  ArrayList<Card > generateDeck(){
        ArrayList<Card> deck = new ArrayList<Card>();
        for( int i = 0; i < SUITS.length; i++){
            for( int j = 0; j < RANKS.length;j++){
                deck.add(new Card(SUITS[i][1], SUITS[i][0], RANKS[j]));
            }
        }
        return deck;
    }

    public  Card randomCard(){
        Random rand = new Random();

        int randomIndex = rand.nextInt(deck.size());
        Card randomCard = deck.get(randomIndex);
        deck.remove(randomIndex);

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
        deck.add(card);
    }

    public void removeCard(Card card){
        deck.remove(card);
    }






}
