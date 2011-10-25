package blackjack.server.models.game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import blackjack.remote.PlayerInterface;
import blackjack.server.models.card.BlackJackCard;

public class Player extends UnicastRemoteObject implements PlayerInterface {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -360255197523560966L;
	
	public int userID;
	
	/**
	 * A player may have multiple hands -- multiple sets of (cards and bet)
	 */
	private ArrayList<Hand> hands = new ArrayList<Hand>();
	
	public Player() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void betAct(int betAmount) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void standAct(int handIndex) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hitAct(int handIndex) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doubleAct(int handIndex) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<Hand> getHands() {
		return hands;
	}

	private class Hand{
		
		private ArrayList<BlackJackCard> cards = new ArrayList<BlackJackCard>();
		private int bet;
		
		public ArrayList<BlackJackCard> getCards() {
			return cards;
		}
		public void addCards(BlackJackCard card) {
			this.cards.add(card);
		}
		public int getBet() {
			return bet;
		}
		public void setBet(int bet) {
			this.bet = bet;
		}
		
		protected int getHighPoint(){
			//TODO
			return 0;
		}
		
		protected int getLowPoint(){
			//TODO
			return 0;
		}
	}
}
