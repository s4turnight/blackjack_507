package blackjack.server.models.game;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import blackjack.remote.CallBackInterface;
import blackjack.remote.PlayerInterface;
import blackjack.server.models.card.BlackJackCard;
import blackjack.server.models.game.events.GameActionListener;
import blackjack.server.models.game.events.SessionEventListener;

public class Player extends UnicastRemoteObject implements PlayerInterface {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8813688965613082827L;
	
	private ArrayList<SessionEventListener> sessionEventListeners;
	
	private ArrayList<GameActionListener> gameActionListeners;
	
	private int amount = 1000;
	
	/**
	 * if all players in a session commit to start game, the game starts
	 */
	private boolean startedGame;
	
	private ArrayList<Hand> hands;
	
	private String name;
	
	private int id;
	
	private int handIndex = 0;
	
	private CallBackInterface callback;
	
	public Player() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public CallBackInterface getCallback() {
		return callback;
	}

	public void setCallback(CallBackInterface callback) {
		this.callback = callback;
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void standAct() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void hitAct() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doubleAct() throws RemoteException {
		// TODO Auto-generated method stub
		
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

}