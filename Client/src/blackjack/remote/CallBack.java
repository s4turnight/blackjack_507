package blackjack.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CallBack extends UnicastRemoteObject implements CallBackInterface {


	/**
	 * generated serial version ID
	 */
	private static final long serialVersionUID = -8483568148184210626L;

	public CallBack() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void notifyGameResult(int[] amountWin) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyGameStart(PlayerInterface[] players)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyMyTurn() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyMove(PlayerInterface player) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	

}
