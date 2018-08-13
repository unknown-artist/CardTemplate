import java.io.*;
class Driver{
	public static void main(String args[])throws IOException{
		Shoe shoe = new Shoe(1, 0);
		Poker poker = new Poker(7, 0, 5, 0, shoe);

		System.out.println("CARDS : ");
		poker.display(poker.handCards);
		// poker.display(poker.tableCards);
		System.out.println("\nBEST HAND : ");
		poker.computeBestHand();
	}
}