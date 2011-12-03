package blackjack.server.models.game;

import java.rmi.RemoteException;

import blackjack.remote.PlayerInterface;
import blackjack.server.models.card.Card;
import blackjack.server.models.card.CardPack;
import blackjack.server.models.game.Player;

public class Game implements GameObservable {
	
	//**********************************************************************************
	// * Data Fields
	// *********************************************************************************
	private Session session;
	
	private Player[] players;
	
	private Dealer dealer;
	
	private int playerTurn;
	
	private CardPack cardPack;
	
	private static final int NUM_CARD_DECKS = 4;
	
	
	//**********************************************************************************
	// * Constructor
	// *********************************************************************************
	/**
	 * Set Players, initial bet: (hands.get[0].bet) has been created in the session class,
	 * @param players
	 * @throws RemoteException 
	 * @requires none;
	 * @modifies this.players, this.cardPack
	 * @effects get players from parameter, card pack gets new instance and shuffled.
	 */
	public Game (Session session, PlayerInterface[] players) throws RemoteException{
		
		this.session = session;
		
		// initialize players
//		for(Player player: players){
//			if( player != null ) player.addGameActionListener(new MyGameActionListener());
//		}
		
		// initialize cards
		cardPack = new CardPack(NUM_CARD_DECKS);
		cardPack.shuffle();
		
		dealer = new Dealer(this);
		
		this.players = new Player[players.length];
		for(int i = 0; i< players.length; i++){
			if(players[i]!=null){
				this.players[i] = (Player)players[i];
				this.players[i].setGame(this);
			}
		}
		
		playerTurn = -1;
		
		System.out.println("Game starts");
		
		start();
	}
	
	public PlayerInterface getDealer(){
		return this.dealer;
	}

	/**
	 * Give two cards to each user
	 * Notify all the players with their cards, bets and dealer's cards
	 * Invoke nextStep()
	 * @throws RemoteException 
	 * @requires cardPack shuffled, player initialized
	 * @modifies playerTurn, players[].hand[0].cards
	 * @effects every player/dealer gets two cards, set the turn to the first player
	 */
	public void start() throws RemoteException{
		
		dealer.addInitialCards();
		
		for(int i = 0; i< players.length ; i++){
			if(players[i] != null){
				players[i].addInitialCards();
			}
		}
		
		notifyClients("Game starts");
		nextStep();
	}
	
	/**
	 * 
	 * @return
	 */
	public Card getNextCard(){
		if(!cardPack.hasNext())
			cardPack.shuffle();
		return cardPack.next();
	}
	
	/**
	 * Dispatch:
	 * If there is more player to move, set turn to next player;
	 * If all players finish, make dealer's move, then getResults 
	 * @throws RemoteException 
	 * @requires start() invoked
	 * @modifies playerTurn 
	 * @effects 
	 */
	public void nextStep(){
		
		//set the previous player's turn to false
		if(playerTurn >= 0 && players[playerTurn]!= null){
			players[playerTurn].setMyTurn(false);
		}
		
		playerTurn++;
		while(playerTurn < players.length ){
			
			//if there is still player to move
			if(players[playerTurn] != null){
				
				//set the player's turn to true
				players[playerTurn].setMyTurn(true);
				System.out.println("Player: " + players[playerTurn].getName() + "'s turn.");
				notifyClients("Player: " + players[playerTurn].getName() + "'s turn.");
				return;
			}
			playerTurn++;
		}
		
		//if no player to move
		System.out.println("Dealer's turn");
		notifyClients("Dealer's turn");
		dealerMove();

		calculateResult();
		
		//clear hands and set player not started
		for(Player player: players){
			if(player!=null){
				player.getHands().clear();
				player.setStarted(false);
			}
		}
		
		notifyClients("Game ends");
	}
	
	/**
	 * Dealer makes decisions after the last player finishes moves.
	 * The dealer hits when his point is below 17, stands otherwise.
	 * @throws RemoteException 
	 * @requires all players finish moves
	 * @modifies dealer.hand
	 * @effects dealer hand changed, 
	 */
	public void dealerMove(){
		
		dealer.showHiddenCard();
		dealer.makeDecisions();
	}
	
	/**
	 * Compute results
	 * @requires all players and dealer finish their move
	 * @modifies this.results
	 * @effects compute the results, give them to this.results
	 */
	public void calculateResult(){
		
		try {
			int dealerPoint = dealer.getHands().get(0).getHighPoint();
			for(Player player: players){
				if(player!=null){
					Hand hand = (Hand)player.getHands().get(0);
					int playerPoint = hand.getHighPoint();
					int bet = hand.getBet();
					if(playerPoint < dealerPoint){
						player.changeAmount(0-bet);
						notifyClients(player.getName()+" loses.");
					}
					else if(playerPoint > dealerPoint){
						player.changeAmount( bet );
						notifyClients(player.getName()+" wins.");
					}
					else
						notifyClients(player.getName()+" draws.");
				}
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void notifyClients( String message ){
		for(Player player: players){
			if(player != null){
				try {
					player.getCallback().update(message, session);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//********************************************************************************
	//* Listener class below
	//***************************************************************************************
	
//	class MyGameActionListener implements GameActionListener{
//		
//		/**
//		 * This action will be fired when a remote player object's standAct() method is invoked
//		 * Simply move the turn to next player
//		 * @requires players[playerTurn] != null
//		 * @modifies playerTurn
//		 * @effects if the current player has another hand, go to that hand, else go to the next player
//		 */
//		@Override
//		public void playerStand(ActionEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//		/**
//		 * This action will be fired when a remote player object's hitAct() method is invoked
//		 * Add One More card
//		 * @requires players[playerTurn] != null && cardPack.hasNext()
//		 * @modifies player[playerTurn].Hand[handIndex].cards, cardPack.currentIndex
//		 * @effects player[playerTurn].Hand[handIndex].cards will add one more card, cardPack.currentIndex ++
//		 */
//		@Override
//		public void playerHit(ActionEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//		/**
//		 * This action will be fired when a remote player object's hitAct() method is invoked
//		 * Add One More card
//		 * @requires players[playerTurn] != null && cardPack.hasNext()
//		 * @modifies player[playerTurn].Hand[handIndex].cards, player[playerTurn].Hand[handIndex].bet, cardPack.currentIndex
//		 * @effects player[playerTurn].Hand[handIndex].cards will add one more card, 
//		 * 			player[playerTurn].Hand[handIndex].bet *= 2,
//		 * 			cardPack.currentIndex ++
//		 * 			if the current player has another hand, go to that hand, else go to the next player 
//		 */
//		@Override
//		public void playerDouble(ActionEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//	}
}
