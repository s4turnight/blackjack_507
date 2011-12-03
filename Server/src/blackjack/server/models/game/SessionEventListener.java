package blackjack.server.models.game;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

public interface SessionEventListener {
	
	public void playerDisconnected(ActionEvent e);
	
	public void playerStartedGame(ActionEvent e) throws RemoteException;
}
