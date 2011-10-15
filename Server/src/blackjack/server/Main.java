package blackjack.server;

import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import blackjack.server.models.card.BlackJackCard;
import blackjack.server.models.card.Card;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		
		jf.setVisible(true);
		

	}

}
