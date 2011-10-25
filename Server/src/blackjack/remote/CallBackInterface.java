package blackjack.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface defines the methods to be implemented in the Player remote class
 * Objects exist on client, and methods to be invoked on Server
 * @author Jiawei Jiang
 *
 */
public interface CallBackInterface extends Remote {
	
	
	/**
	 * Provide the initial cards and bets information of all players
	 * @param bets
	 * @throws RemoteException
	 */
	public void notifyGameStart(PlayerInterface[] players) throws RemoteException;
	
	/**
	 * Let a player take turn to make decision 
	 * @throws RemoteException
	 */	
	public void notifyMyTurn() throws RemoteException;
	
	/**
	 * 
	 * @param player
	 * @throws RemoteException
	 */
	public void notifyMove(PlayerInterface player) throws RemoteException;
	
	/**
	 * Integer values could be either positive or negative
	 * @throws RemoteException
	 */
	public void notifyGameResult(int[] amountWin) throws RemoteException;

}
