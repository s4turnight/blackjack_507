package blackjack.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface defines the methods to be implemented in the Game remote class
 * Objects exist on server, and methods to be invoked on client 
 * @author Jiawei Jiang
 */
public interface PlayerInterface extends Remote {
	
	public void disconnect() throws RemoteException;
	
	public void start(int betAmount) throws RemoteException;
	
	public void standAct() throws RemoteException;
	
	public void hitAct() throws RemoteException;
	
	public void doubleAct() throws RemoteException;
	
}
