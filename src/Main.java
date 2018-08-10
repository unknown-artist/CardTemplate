public class Main {

    public static void main(String[] args) {

        Card c1 = new Card ("", "Hearts", "", 1);
        Card c2 = new Card ("", "Hearts", "", 13);

        Shoe shoe  = new Shoe(1, 2);

        while( shoe.shoeCards.size() != 0){
            System.out.print( shoe.randomNCard(3));
            System.out.println( "  " + shoe.shoeCards.size());

        }

        //System.out.println(c1.suitValue());
    }
}
