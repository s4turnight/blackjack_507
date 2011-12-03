package blackjack.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import blackjack.client.gui.MainFrame;

public class CallBack extends UnicastRemoteObject implements CallBackInterface {


	/**
	 * generated serial version ID
	 */
	private static final long serialVersionUID = -8483568148184210626L;
	
	private MainFrame mainFrame;

	public CallBack() throws RemoteException {
		super();
	}
	
	public void setMainFrame(MainFrame frame){
		this.mainFrame = frame;
	}
	
	public void notifyNewPlayer(PlayerInfoInterface[] playersinfo) throws RemoteException {
//		for(PlayerInfoInterface pi: playersinfo){
//			System.out.println("Player "+pi.getId()+" is now in the game.");
//		}
		System.out.println("callback works");
	}
	
	@Override
	public void notifyGameResult(int[] amountWin) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyGameStart(PlayerInfoInterface[] players)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyMyTurn() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyMove(PlayerInfoInterface player) throws RemoteException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void notifyNewPlayer(int id, String name) throws RemoteException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(String message, SessionInterface session) throws RemoteException {
		
		System.out.println(message);
		mainFrame.refresh(session, message);

	}
}
