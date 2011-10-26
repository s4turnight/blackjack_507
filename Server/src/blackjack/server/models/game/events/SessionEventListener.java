package blackjack.server.models.game.events;

import java.awt.event.ActionEvent;

public interface SessionEventListener {
	
	public void playerDisconnected(ActionEvent e);
	
	public void playerStartedGame(ActionEvent e);
}
