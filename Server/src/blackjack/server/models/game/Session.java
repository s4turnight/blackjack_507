package blackjack.server.models.game;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import blackjack.remote.CallBackInterface;
import blackjack.remote.PlayerInterface;
import blackjack.remote.SessionInterface;

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
	private static final int MAX_PLAYER_NUM = 3;
	
	// singleton instance
	private static Session session = null;
	
	private Game game;
	
	/**
	 * Array with fixed size, storing players or null, array index as seat number
	 */
	private PlayerInterface[] players = new PlayerInterface[MAX_PLAYER_NUM];
	
	//**********************************************************************************
	// * Constructor
	// *********************************************************************************
	private Session() throws RemoteException {
		super();
	}
	
	public static Session getInstance() throws RemoteException{
		if(session == null){
			return new Session();
		}
		else return session;
	}

	
	//**********************************************************************************
	// * Remote Methods
	// ***********************************************************************************

	@Override
	public PlayerInterface connect(CallBackInterface callback, String name)	throws RemoteException {
				
		for(int i = 0; i < players.length; i++ ){
			if(players[i] == null){
				
				//notify all: new player comes
				for(PlayerInterface player: players){
					if(player!=null){
						((Player)player).getCallback().update("New Player "+name+" enters\n", this);
					}
				}
				
				// add that player
				players[i] = new Player();
				((Player)players[i]).setId(i);
				((Player)players[i]).setName(name);
				((Player)players[i]).setCallback(callback);
				
				System.out.println(players[i].getName() + " connected at "+ new java.util.Date());
				((Player)players[i]).addSessionEventListener(new MySessionEventListener());
				
				// return the player to the client, so that the client can invoke action methods from the player object
				return players[i];
			} 
		}
		return null;
	}
	
	
	@Override
	public String[] getNames() throws RemoteException {
		String[] names = new String[MAX_PLAYER_NUM];
		for(int i = 0; i<players.length; i++){
			names[i] = (players[i]==null)? null : players[i].getName();
		}
		return names;
	}

	@Override
	public int[] getAmounts() throws RemoteException {
		int[] amounts = new int[MAX_PLAYER_NUM];
		for(int i = 0; i<players.length; i++){
			amounts[i] = (players[i]==null)? null : players[i].getAmount();
		}
		return amounts;
	}
	
	public PlayerInterface[] getPlayers() throws RemoteException{
		return this.players;
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
		 * @throws RemoteException 
		 * @requires gameStarted == false
		 * @modifies the player oject which fires the event, in the session
		 * @effects player.startGame = true; if all player ready to start, start new game.
		 */
		@Override
		public void playerStartedGame(ActionEvent e) throws RemoteException {

			Player player = (Player)e.getSource();
			player.setStarted(true);
			
			if(isAllPlayersReady()){
				game = new Game(Session.this, players);
				
//				for(Player everyPlayer: players){
//					everyPlayer.getCallback().notifyGameStart(null);
//				}
			}
		}
	}
	
	//**********************************************************************************
	// * Internal methods
	// ***********************************************************************************
	
	/**
	 * @return true if all players clicked to start, false otherwise
	 */
	private boolean isAllPlayersReady(){
		for(PlayerInterface player: players){
			try {
				if( (player!=null) && !player.isStarted()) return false;
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public PlayerInterface getDealer() throws RemoteException {
		if(this.game!=null){
			return this.game.getDealer();
		}
		else return null;
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
//		
//	}
}
