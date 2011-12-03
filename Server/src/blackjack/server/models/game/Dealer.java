package blackjack.server.models.game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

import blackjack.remote.HandInterface;
import blackjack.remote.PlayerInterface;

public class Dealer extends UnicastRemoteObject implements PlayerInterface {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3362182476657301109L;

	private LinkedList<HandInterface> hands;
	
	private Game game;
	
	public Dealer(Game game) throws RemoteException {
		super();
		this.game = game;
	}
	
	//******************************************************************************************
	// * Methods invoked in Game.class
	//******************************************************************************************	
	public void addInitialCards() throws RemoteException{
		
		HandInterface hand = new Hand(0);
		((Hand)hand).addCard(game.getNextCard());
		((Hand)hand).addCard(game.getNextCard());
		((Hand)hand).getCards().get(0).visible = false;
		
		hands = new LinkedList<HandInterface>();
		hands.add(hand);
	}
	
	public void showHiddenCard(){
		((Hand)hands.get(0)).getCards().get(0).visible = true;
	}
	
	public void makeDecisions(){
		Hand hand = (Hand)hands.get(0);
		try {
			while(hand.getLowPoint()<17){
				this.hitAct();
			}
		} 
		catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void disconnect() throws RemoteException {}


	@Override
	public void start(int betAmount) throws RemoteException {}


	@Override
	public boolean isMyTurn() throws RemoteException {
		return false;
	}


	@Override
	public void standAct() throws RemoteException {
		game.notifyClients("Dealer Stands");
	}


	@Override
	public void hitAct() throws RemoteException {
		Hand hand = (Hand)hands.get(0);
		hand.addCard(game.getNextCard());
		
		game.notifyClients("Dealer Hits");
	}


	@Override
	public void doubleAct() throws RemoteException {}


	@Override
	public String getName() throws RemoteException {
		// TODO Auto-generated method stub
		return "Dealer";
	}


	@Override
	public int getAmount() throws RemoteException {
		// TODO Auto-generated method stub
		return -1;
	}


	@Override
	public int getId() throws RemoteException {
		// TODO Auto-generated method stub
		return -1;
	}


	@Override
	public boolean isStarted() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}


	public LinkedList<HandInterface> getHands() throws RemoteException {
		return this.hands;
	}

	@Override
	public HandInterface getHand(int index) throws RemoteException {
		return hands.get(index);
	}
}
