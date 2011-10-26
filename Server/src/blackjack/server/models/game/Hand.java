package blackjack.server.models.game;

import java.util.ArrayList;

import blackjack.server.models.card.Card;

public class Hand {
	
	//******************************************************************************************
	// * Data Fields
	//******************************************************************************************
	private ArrayList<Card> cards;
	private int bet;
	
	//******************************************************************************************
	// * Setter and Getters
	//******************************************************************************************
	public ArrayList<Card> getCards() {
		return cards;
	}

	public int getBet() {
		return bet;
	}
	
	public void setBet(int bet) {
		this.bet = bet;
	}
	
	public void addCard(Card card){
		this.cards.add(card);
	}
	
	/**
	 * Return the highest score value which does not exceeds 21, return 0 if busted
	 * TODO specification
	 */
	public int getHighScore(){
		return 0;
	}
	
}
