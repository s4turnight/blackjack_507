package blackjack.server.models.game;

import java.awt.event.ActionEvent;

import blackjack.server.models.card.CardPack;
import blackjack.server.models.game.Player;
import blackjack.server.models.game.events.GameActionListener;

public class Game{
	
	//**********************************************************************************
	// * Data Fields
	// *********************************************************************************
	private Player[] players;
	
	private int playerTurn = -1;
	
	private CardPack cardPack;
	
	//**********************************************************************************
	// * Constructor
	// *********************************************************************************
	/**
	 * Set Players, initial bet: (hands.get[0].bet) has been created in the session class,
	 * @param players
	 */
	public Game (Player[] players){
		this.players = players;
		for(Player player: players){
			if( player != null ) player.addGameActionListener(new MyGameActionListener());
		}
	}
	
	/**
	 * Send two cards to each user
	 * Set the playerTurn to the first player
	 * @requires	TODO
	 * @modifies playerTurn, players[].hand[0].cards
	 * @effects TODO
	 */
	public void start(){
		
	}
	
	//********************************************************************************
	//* Listener class below
	//***************************************************************************************
	
	class MyGameActionListener implements GameActionListener{
		
		/**
		 * This action will be fired when a remote player object's standAct() method is invoked
		 * Simply move the turn to next player
		 * @requires players[playerTurn] != null
		 * @modifies playerTurn
		 * @effects if the current player has another hand, go to that hand, else go to the next player
		 */
		@Override
		public void playerStand(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		/**
		 * This action will be fired when a remote player object's hitAct() method is invoked
		 * Add One More card
		 * @requires players[playerTurn] != null && cardPack.hasNext()
		 * @modifies player[playerTurn].Hand[handIndex].cards, cardPack.currentIndex
		 * @effects player[playerTurn].Hand[handIndex].cards will add one more card, cardPack.currentIndex ++
		 */
		@Override
		public void playerHit(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		/**
		 * This action will be fired when a remote player object's hitAct() method is invoked
		 * Add One More card
		 * @requires players[playerTurn] != null && cardPack.hasNext()
		 * @modifies player[playerTurn].Hand[handIndex].cards, player[playerTurn].Hand[handIndex].bet, cardPack.currentIndex
		 * @effects player[playerTurn].Hand[handIndex].cards will add one more card, 
		 * 			player[playerTurn].Hand[handIndex].bet *= 2,
		 * 			cardPack.currentIndex ++
		 * 			if the current player has another hand, go to that hand, else go to the next player 
		 */
		@Override
		public void playerDouble(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
