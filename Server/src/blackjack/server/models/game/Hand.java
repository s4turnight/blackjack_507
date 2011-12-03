package blackjack.server.models.game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

import blackjack.remote.HandInterface;
import blackjack.server.models.card.Card;
import blackjack.server.models.card.BlackJackCard;

public class Hand extends UnicastRemoteObject implements HandInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5779984239647566674L;


	//******************************************************************************************
	// * Data Fields
	//******************************************************************************************
	private LinkedList<Card> cards;
	private int bet;

	
	public Hand(int bet) throws RemoteException {
//		super();
		cards = new LinkedList<Card>();
		this.bet = bet;
	}
	
	//******************************************************************************************
	// * Setter and Getters
	//******************************************************************************************
	public LinkedList<Card> getCards() {
		return cards;
	}

	public int getBet() throws RemoteException{
		return bet;
	}
	
	public void setBet(int bet) {
		this.bet = bet;
	}
	
	public void addCard(Card card){
		this.cards.add(card);
	}
	
	public void doubleBet(){
		bet *= 2;
	}
	
	/**
	 * Return the highest score value which does not exceeds 21, return 0 if busted
	 */
	@Override
	public int getHighPoint() throws RemoteException {
		int totalPoint = 0;
		
		int numOfAces = 0;
		int highestPoint = 0;
		
		for(int i=0; i < cards.size(); i++){
			BlackJackCard bjc = (BlackJackCard)cards.get(i);
			if(bjc.getValue() == 1)
				numOfAces ++;
			int point = bjc.getPoint();
			if(point == 1)
				point = 11;
			highestPoint += point;
		}
		
		while( highestPoint > 21 && numOfAces > 0 ){
			highestPoint -= 10;
			numOfAces --;
		}
		
		if( highestPoint < 22)
			totalPoint = highestPoint;
		
		return totalPoint;
	}
	
	@Override
	public int getLowPoint() throws RemoteException{
		
		int totalPoint = 0;
		
		for(int i=0; i < cards.size(); i++){
			BlackJackCard bjc = (BlackJackCard)cards.get(i);
			int point = bjc.getPoint();
			totalPoint += point;
		}
		return totalPoint;
	}
	
	@Override
	public LinkedList<int[]> getCardsIntArray() throws RemoteException {
		// 
		LinkedList<int[]> cardsInts = new LinkedList<int[]>();
		for(Card card: cards){
			cardsInts.add(card.toIntArray());
		}
		return cardsInts;
	}
}