package blackjack.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface defines the methods to be implemented in the Game remote class
 * Objects exist on server, and methods are to be invoked on client as that player's actions 
 * @author Jiawei Jiang
 */
public interface PlayerActionInterface extends Remote {
	
	public void disconnect() throws RemoteException;
	
	public void start(int betAmount) throws RemoteException;
	
	public boolean isMyTurn() throws RemoteException;
	
	public void standAct() throws RemoteException;
	
	public void hitAct() throws RemoteException;
	
	public void doubleAct() throws RemoteException;
	
}
