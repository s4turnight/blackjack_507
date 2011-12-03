package blackjack.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

public interface HandInterface extends Remote {
	
	public int getHighPoint() throws RemoteException;
	
	public int getLowPoint() throws RemoteException;
	/**
	 * ArrayList of int[2]: (suit, value)
	 * @return 
	 */
	public LinkedList<int[]> getCardsIntArray() throws RemoteException;
	
	public int getBet() throws RemoteException;

	
}
