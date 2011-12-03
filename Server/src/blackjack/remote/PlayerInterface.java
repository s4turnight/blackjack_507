package blackjack.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

/**
 * This interface defines the methods to be implemented in the Game remote class
 * Objects exist on server, and methods are to be invoked on client as that player's actions 
 * @author Jiawei Jiang
 */
public interface PlayerInterface extends Remote {
	
	public void disconnect() throws RemoteException;
	
	public void start(int betAmount) throws RemoteException;
	
	public boolean isMyTurn() throws RemoteException;
	
	public void standAct() throws RemoteException;
	
	public void hitAct() throws RemoteException;
	
	public void doubleAct() throws RemoteException;
	
	public String getName() throws RemoteException;
	
	public int getAmount() throws RemoteException;
	
	public int getId() throws RemoteException;
	
	public boolean isStarted() throws RemoteException;
	
	public HandInterface getHand(int index) throws RemoteException;
}
