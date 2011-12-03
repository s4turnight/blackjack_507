package blackjack.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.LinkedList;

import blackjack.client.gui.CardPanel;
import blackjack.client.gui.MainFrame;
import blackjack.remote.CallBack;
import blackjack.remote.CallBackInterface;
import blackjack.remote.PlayerInterface;
import blackjack.remote.SessionInterface;

public class ClientMain {
	
	private static final String NAME = "Perry";
	// Server address
	private static final String SERVER_ADDRESS = "";	// "" means local server
	
	public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException{
//		
//		System.setProperty("java.security.policy", "client.policy");
//		
//		if (System.getSecurityManager() == null) { 
//			System.setSecurityManager(new RMISecurityManager ()); 
//		} 
//		
		SessionInterface session = (SessionInterface) LocateRegistry.getRegistry(SERVER_ADDRESS).lookup("session");
		CallBackInterface callback = new CallBack();
		
		PlayerInterface player = session.connect(callback, NAME);
		
		if(player==null){
			System.out.println("No seat available for now");
			return;
		}
		
		MainFrame mf = new MainFrame(player, session);
		((CallBack)callback).setMainFrame(mf);
		mf.setVisible(true);

	
//		System.out.println(player.getHand(0).getHighPoint());
//		System.out.println(session.getDealer().getHand(0).getHighPoint());
//		System.out.println(player.getHand(0).getBet());
//
//		LinkedList<int[]> list =  session.getDealer().getHand(0).getCardsIntArray();
//		for(int i=0; i<list.size(); i++){
//			System.out.println(CardPanel.intArrayToString(list.get(i))+"\n");
//		}
//		mf.refresh(session);
	}
}
