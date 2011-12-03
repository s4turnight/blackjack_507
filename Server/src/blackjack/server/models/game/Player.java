package blackjack.server.models.game;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;

import blackjack.remote.CallBackInterface;
import blackjack.remote.HandInterface;
import blackjack.remote.PlayerInterface;
import blackjack.server.models.game.events.GameActionListener;
import blackjack.server.models.game.Hand;

public class Player extends UnicastRemoteObject implements PlayerInterface, PlayerObserver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8813688965613082827L;
	
	/**
	 * List of SessionEventListener, event source: Player, do something to the Session
	 * @methods addSessionEventListener
	 */
	private ArrayList<SessionEventListener> sessionEventListeners;
	
	/**
	 * List of GameActionListener, event source: Player, do something to the Game
	 * @methods addGameActionListener
	 */
	private ArrayList<GameActionListener> gameActionListeners;
	
	/**
	 * the total amount of a player's money account.
	 * @methods in Game.class
	 */
	private int amount = 1000;
	
	private Game game;
	
	/**
	 * Whether a player clicked to start a new game, default value is false
	 * if all players in a session commit to start game, the game starts
	 * @methods isStarted, setStarted
	 */
	private boolean started = false;
	
	private boolean myTurn = false;
	
	private LinkedList<HandInterface> hands;
	
	private String name;
	
	private int id;
	
	private int handIndex = 0;
	
	private CallBackInterface callback;
	
	public Player() throws RemoteException {
		super();
	}
	
	//******************************************************************************************
	// * Methods invoked in Game.class
	//******************************************************************************************	
	public void addInitialCards() throws RemoteException{
		
		Hand hand = (Hand)(hands.get(0));
		hand.addCard(game.getNextCard());
		hand.addCard(game.getNextCard());

	}
	/**
	 * 
	 * @return
	 */
	public boolean hasNextHand(){
		return handIndex < ( hands.size()-1) ;
	}
	
	//******************************************************************************************
	// * Setter and Getters
	//******************************************************************************************
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public HandInterface getHand(int index) {
		if(hands !=null && hands.size()>0){
			return this.hands.get(index);
		}
		else return null;
	}
	
	public LinkedList<HandInterface> getHands(){
		return this.hands;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int getAmount() {
		return amount;
	}
	
	public void setGame(Game game){
		this.game = game;
	}
	
	/**
	 * Add or cut the player's account amount.
	 * @param win can be either positive or negative value.
	 * @modifies this.amount
	 */
	public void changeAmount(int win){
		this.amount += win;
	}
	
	public CallBackInterface getCallback() {
		return callback;
	}

	public void setCallback(CallBackInterface callback) {
		this.callback = callback;
	}

	@Override
	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean startedGame) {
		this.started = startedGame;
	}
	
	public void setMyTurn(boolean myTurn){
		this.myTurn = myTurn;
	}
	
	@Override
	public boolean isMyTurn() throws RemoteException {
		return this.myTurn;
	}

	//******************************************************************************************
	//* Listener Adding methods 
	//******************************************************************************************
	/** 
	 * 
	 * @param sel
	 */
	public void addSessionEventListener(SessionEventListener sel){
		if(sessionEventListeners == null){
			sessionEventListeners = new ArrayList<SessionEventListener>(2);
		}
		sessionEventListeners.add(sel);
	}
	
	/**
	 * 
	 * @param sel
	 */
	public void addGameActionListener(GameActionListener sel){
		if(gameActionListeners == null){
			gameActionListeners = new ArrayList<GameActionListener>(2);
		}
		gameActionListeners.add(sel);
	}
	
	
	//******************************************************************************************
	//* Remote Methods Below, they each invokes certain method to fire event 
	//******************************************************************************************
	
	@Override
	public void disconnect() throws RemoteException {
		processDisconnect(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
	}
	
	@Override
	public void start(int betAmount) throws RemoteException {
		if(this.started)return;
		HandInterface hand = new Hand(betAmount);
		this.hands = new LinkedList<HandInterface>();
		this.hands.add(hand);
		processStart(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
	}
	
	/**
	 * This action will be fired when a remote player object's standAct() method is invoked
	 * Simply move the turn to next player
	 * @requires player != null
	 * @modifies playerTurn
	 * @effects if the current player has another hand, go to that hand, else go to the next player
	 */
	@Override
	public void standAct() throws RemoteException {
		if(!this.started)return;
		if(!this.myTurn)return;
		game.notifyClients("Player " + name + " stands");
		game.nextStep();
	}
	
	/**
	 * This action will be fired when a remote player object's hitAct() method is invoked
	 * Add One More card
	 * @requires player != null && cardPack.hasNext()
	 * @modifies player.Hand[handIndex].cards, cardPack.currentIndex
	 * @effects player.Hand[handIndex].cards will add one more card, cardPack.currentIndex ++
	 */
	@Override
	public void hitAct() throws RemoteException {
		if(!this.started)return;
		if(!this.myTurn)return;
		
		Hand hand = (Hand)hands.get(handIndex);
		hand.addCard(game.getNextCard());

		//update
		game.notifyClients("Player " + name + " hits");
		
		//check bust or not, if yes: nextStep
		if(hand.getLowPoint()>21){
			game.nextStep();
		}
	}
	
	/**
	 * This action will be fired when a remote player object's hitAct() method is invoked
	 * Add One More card
	 * @requires player != null && cardPack.hasNext()
	 * @modifies player.Hand[handIndex].cards, player.Hand[handIndex].bet, cardPack.currentIndex
	 * @effects player.Hand[handIndex].cards will add one more card, 
	 * 			player.Hand[handIndex].bet *= 2,
	 * 			cardPack.currentIndex ++
	 * 			if the current player has another hand, go to that hand, else go to the next player 
	 */
	@Override
	public void doubleAct() throws RemoteException {
		if(!this.started)return;
		if(!this.myTurn)return;
		((Hand)hands.get(handIndex)).addCard(game.getNextCard());
		((Hand)hands.get(handIndex)).doubleBet();
		
		game.notifyClients("Player " + name + " doubles");
		
		game.nextStep();
	}
	
	//******************************************************************************************
	//* Event fire methods Below
	//******************************************************************************************
	/**
	 * fire the event
	 * @param e
	 */
	void processDisconnect(ActionEvent e){
		if( sessionEventListeners != null){
			for(int i=0; i < sessionEventListeners.size() ; i++ ){
				sessionEventListeners.get(i).playerDisconnected(e);
			}
		}
	}
	
	void processStart(ActionEvent e) throws RemoteException{
		if( sessionEventListeners != null){
			for(int i=0; i < sessionEventListeners.size() ; i++ ){
				sessionEventListeners.get(i).playerStartedGame(e);
			}
		}
	}
}