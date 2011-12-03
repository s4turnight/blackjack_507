package blackjack.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

public interface PlayerInfoInterface extends Remote{
	
	public String getName() throws RemoteException;
	
	public int getAmount() throws RemoteException;
	
	public int getId() throws RemoteException;
	
	public boolean isStarted() throws RemoteException;
	
	public LinkedList<HandInterface> getHands() throws RemoteException;
	
}
