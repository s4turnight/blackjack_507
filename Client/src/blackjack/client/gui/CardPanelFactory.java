package blackjack.client.gui;

import java.rmi.RemoteException;
import java.util.LinkedList;

import blackjack.remote.HandInterface;

public class CardPanelFactory {
	
	//singleton
	private static CardPanelFactory cardPanelFactory = null;
	
	private CardPanelFactory(){
	}
	
	public static CardPanelFactory getInstance(){
		if(cardPanelFactory == null){
			cardPanelFactory = new CardPanelFactory(); 
		}
		return cardPanelFactory;
	}
	
	public CardPanel makePanel(){
		try {
			return new CardPanel();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
