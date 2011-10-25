package blackjack.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface defines the methods to be implemented in the Game remote class
 * Objects exist on server, and methods to be invoked on client 
 * @author Jiawei Jiang
 */
public interface PlayerInterface extends Remote {
	
	public void betAct(int betAmount) throws RemoteException;
	
	public void standAct(int handIndex) throws RemoteException;
	
	public void hitAct(int handIndex) throws RemoteException;
	
	public void doubleAct(int handIndex) throws RemoteException;
	
}
