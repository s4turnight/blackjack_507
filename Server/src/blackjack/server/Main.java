package blackjack.server;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JFrame;
import javax.swing.JPanel;

import blackjack.remote.SessionInterface;
import blackjack.server.models.card.BlackJackCard;
import blackjack.server.models.card.Card;
import blackjack.server.models.card.CardPack;
import blackjack.server.models.game.Player;
import blackjack.server.models.game.Session;
import blackjack.server.models.game.events.SessionEventListener;

public class Main {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		SessionInterface session = new Session();
		Registry registry = LocateRegistry.getRegistry();
		registry.rebind("session", session);
		System.out.println("Server start!");
	
	}
	

	
	public static void testCardImage(){
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
		CardPack cs = new CardPack(4);
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
