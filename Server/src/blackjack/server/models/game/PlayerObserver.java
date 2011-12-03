package blackjack.server.models.game;

import blackjack.remote.CallBackInterface;

public interface PlayerObserver {
	
	public void setCallback(CallBackInterface callback);
	
	public CallBackInterface getCallback();
	
}
