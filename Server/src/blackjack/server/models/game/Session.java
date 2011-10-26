package blackjack.server.models.game;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import blackjack.remote.CallBackInterface;
import blackjack.remote.SessionInterface;
import blackjack.server.models.game.events.SessionEventListener;

/**
 * This class represents a game session between the server and multiple clients.
 * @author jjw
 *
 */
public class Session extends UnicastRemoteObject implements SessionInterface {
	
	//**********************************************************************************
	// * Data Fields
	// *********************************************************************************
	/**
	 * 
	 */
	private static final long serialVersionUID = 2045109316660480758L;

	// maximum number of players
	private static final int MAX_PLAYER_NUM = 6;
	
	/**
	 * Game (Singleton)
	 */
	private Game game;
	
	private boolean gameStarted = false;
	
	/**
	 * Array with fixed size, storing players or null, array index as seat number
	 */
	private Player[] players = new Player[MAX_PLAYER_NUM];
	
	//**********************************************************************************
	// * Constructor
	// *********************************************************************************
	public Session() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//**********************************************************************************
	// * Getters and Setters
	// *********************************************************************************
	public boolean isGameStarted() {
		return gameStarted;
	}

	
	//**********************************************************************************
	// * Remote Methods
	// ***********************************************************************************

	/**
	 * 
	 */
	@Override
	public Player connect(CallBackInterface callback, String name)	throws RemoteException {
		
		for(int i = 0; i < players.length; i++ ){
			if(players[i] == null){
				players[i] = new Player();
				players[i].setId(i);
				players[i].setName(name);
				players[i].setCallback(callback);
				System.out.println("Player " + name + " connected at "+ new java.util.Date());
				players[i].addSessionEventListener(new MySessionEventListener());
				return players[i];
			}
		}
		return null;
	}


	//**********************************************************************************
	// * Listener Class
	// ***********************************************************************************
	
	class MySessionEventListener implements SessionEventListener{
		
		/**
		 * This action will be fired when a remote player object's disconnect() method is invoked
		 * Remove the player from the session if the game is not in progress 
		 * @requires none
		 * @modifies players
		 * @effects the player object which fires the event will be removed from the list  
		 */
		@Override
		public void playerDisconnected(ActionEvent e) {
			Player player = (Player)e.getSource();
			int id = player.getId();
			String name = player.getName(); 
			players[id] = null;
			System.out.println(name +" has quit the game.");
		}

		/**
		 * This action will be fired when a remote player object's start(int bet) method is invoked
		 * Set the player status "startGame" to true; 
		 * Check then: if all player ready to start, start new game
		 * @requires gameStarted == false
		 * @modifies the player oject which fires the event, in the session
		 * @effects player.startGame = true; if all player ready to start, start new game.
		 */
		@Override
		public void playerStartedGame(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

	
// ******************************************************************************************
//                                    Old Design below
// ******************************************************************************************
//	@Override
//	public int connect(CallBackInterface callback, String name) throws RemoteException {
//		for(int i = 0; i< players.length; i++){
//			if( players[i] == null){
//				players[i] = new Player();
//				callbacks[i] = callback;
//				return i;
//			}
//		}
//		return -1;
//	}
//
//	@Override
//	public boolean disconnect(int playerIndex) throws RemoteException {
//		if(game.isStarted() == true)return false;
//		else return true;
//	}
//
//	@Override
//	public void readyForGame(int playerIndex) throws RemoteException {
//		// TODO Auto-generated method stub
//		
//	}
}
