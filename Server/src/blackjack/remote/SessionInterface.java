package blackjack.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * This interface defines the methods to be implemented in the Session remote class
 * Objects exist on server, and methods to be invoked on client
 * @author Jiawei Jiang
 */
public interface SessionInterface extends Remote {
	
	/**
	 * Connect to the server session and return an integer indicating the player index
	 * @return
	 * @throws RemoteException
	 */
	public int connect(CallBackInterface callback, String name) throws RemoteException;
	
	/**
	 * Disconnect from the server session
	 * @param playerIndex
	 * @return
	 * @throws RemoteException
	 */
	public boolean disconnect(int playerIndex) throws RemoteException;
	
	/**
	 * A player get ready for a new game, when all players get ready, new game start 
	 * @param playerIndex
	 * @return
	 * @throws RemoteException
	 */
	public void readyForGame(int playerIndex) throws RemoteException;
}
