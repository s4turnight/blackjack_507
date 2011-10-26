package blackjack.server.models.game.events;

import java.awt.event.ActionEvent;

public interface GameActionListener {
	
	public void playerStand(ActionEvent e);

	public void playerHit(ActionEvent e);
	
	public void playerDouble(ActionEvent e);
}
