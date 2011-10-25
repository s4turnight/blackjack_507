package blackjack.server.models.game;

import blackjack.server.models.game.Player;

public class Game{
	
	private boolean started = false;
	private Player[] players;

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}
	
	/**
	 * 
	 * @param users
	 */
	public void createPlayersFromUsers(User[] users){
		
	}
}
