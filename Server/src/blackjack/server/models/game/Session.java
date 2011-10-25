package blackjack.server.models.game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import blackjack.remote.CallBackInterface;
import blackjack.remote.SessionInterface;

/**
 * This class represents a game session between the server and multiple clients.
 * @author jjw
 *
 */
public class Session extends UnicastRemoteObject implements SessionInterface {
	
	public Session() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2045109316660480758L;

	// maximum number of players
	private static final int MAX_PLAYER_NUM = 6;
	
	/**
	 * Game (Singleton)
	 */
	private static Game game;
	
	/**
	 * Array with fixed size, storing players or null, array index as seat number
	 */
	private static User[] users = new User[MAX_PLAYER_NUM];
	
	/**
	 * 
	 */
	public static CallBackInterface[] callbacks = new CallBackInterface[MAX_PLAYER_NUM];

	@Override
	public int connect(CallBackInterface callback, String name) throws RemoteException {
		for(int i = 0; i<users.length; i++){
			if(users[i] == null){
				users[i] = new User(i, name);
				callbacks[i] = callback;
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean disconnect(int playerIndex) throws RemoteException {
		if(game.isStarted() == true)return false;
		else return true;
	}

	@Override
	public void readyForGame(int playerIndex) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}
