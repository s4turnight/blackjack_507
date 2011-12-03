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
	public PlayerInterface connect(CallBackInterface callback, String name) throws RemoteException;
	
	/**
	 * Return all players' names in an int array
	 * @return
	 * @throws RemoteException
	 */
	public String[] getNames() throws RemoteException;
	
	/**
	 * Return all players' amounts in an int array
	 * @return
	 * @throws RemoteException
	 */
	public int[] getAmounts() throws RemoteException;
	
	
	public PlayerInterface[] getPlayers() throws RemoteException;
	
	public PlayerInterface getDealer() throws RemoteException;
		
	
	// ******************************************************************************************
	//  Old Design below
	//******************************************************************************************	
	/**
	 * Disconnect from the server session
	 * @param playerIndex
	 * @return
	 * @throws RemoteException
	 */
//	public boolean disconnect(int playerIndex) throws RemoteException;
	
	/**
	 * A player get ready for a new game, when all players get ready, new game start 
	 * @param playerIndex
	 * @return
	 * @throws RemoteException
	 */
//	public void readyForGame(int playerIndex) throws RemoteException;
}
