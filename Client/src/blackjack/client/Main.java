package blackjack.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import blackjack.remote.CallBack;
import blackjack.remote.CallBackInterface;
import blackjack.remote.PlayerInterface;
import blackjack.remote.SessionInterface;

public class Main {
	
	// Server address
	private static final String SERVER_ADDRESS = "";	// "" means local server
	
	public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException{
		SessionInterface session = (SessionInterface) LocateRegistry.getRegistry(SERVER_ADDRESS).lookup("session");
		CallBackInterface callback = new CallBack();
		
		PlayerInterface player = session.connect(callback, "abc");


		Thread.sleep(10000);
		
		player.disconnect();
		
	}
}
