package blackjack.server;

import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import blackjack.server.models.card.BlackJackCard;
import blackjack.server.models.card.Card;
import blackjack.server.models.card.CardsSet;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//test image
		final Card b = new BlackJackCard(2,3);
		JFrame jf = new JFrame();
		JPanel jp = new JPanel(){
			public void paintComponent(Graphics g) {
		        try {
					g.drawImage(b.getImage(), 0, 0, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		};
		jf.add(jp);
		//jf.setVisible(true);
		
		//test shuffling
		int[] counters = new int[4];
		CardsSet cs = new CardsSet(4);
		cs.shuffle();
		while(cs.hasNext()){
			Card c = cs.next();
			System.out.print(c.toNumericString()+", ");
			counters[c.getSuit()]++;
		}
		System.out.println();
		for(int counter: counters){
			System.out.println(counter);
		}
	}

}
