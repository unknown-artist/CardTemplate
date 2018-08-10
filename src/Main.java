public class Main {

    public static void main(String[] args) {

        Card c1 = new Card ("", "Hearts", "", 1);
        Card c2 = new Card ("", "", "", 13);

        System.out.println(c1.suitValue());
    }
}
