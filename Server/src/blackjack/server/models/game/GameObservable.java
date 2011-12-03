package blackjack.server.models.game;

public interface GameObservable {
	
	public void notifyClients(String message);
}
