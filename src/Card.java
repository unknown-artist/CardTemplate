public class Card {
    public String suit;
    public String rank;
    public String color;
    public int value;
    public String suitUnicode;
    public String rankString;

    public Card( String color, String suit, String rank, int value, String suitUnicode, String rankString){
        this.suit = suit;
        this.rank = rank;
        this.color = color;
        this.value = value;
        this.suitUnicode = suitUnicode;
        this.rankString = rankString;
    }

    public int compareValue(Card card){
        int val1 = value == 1 && card.value == 13 ? 14 : value;
        int val2 = card.value == 1 && value == 13 ? 14 : card.value;

        if( val1 < val2){
            return -1;
        } else if ( val1 > val2){
            return 1;
        }
        return 0;
    }

    public boolean compareSuit(Card card){
        return suit.equals(card.suit);
    }

    public int suitValue(){
        final String[] SUITS = {"Clubs", "Spades","Diamonds", "Hearts"};
        for( int i = 0; i < SUITS.length; i++) {
            if (suit.equals(SUITS[i])) {
                return i;
            }

        }
        return -1;
    }
    
    @Override
    public String toString(){
        return rankString + suitUnicode;
    }

}
