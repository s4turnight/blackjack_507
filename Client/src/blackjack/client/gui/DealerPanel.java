package blackjack.client.gui;

import java.awt.GridLayout;
import java.rmi.RemoteException;

import javax.swing.JLabel;

import blackjack.remote.PlayerInterface;

public class DealerPanel extends PlayerPanelTemplate{
	
	public DealerPanel(PlayerInterface dealer) {
		super(dealer);
	}

	@Override
	public void initialize() {
		this.setSize(200, 200);
	}

	@Override
	public void addInfoPanel() {
		infoLabel = new JLabel("Dealer");
		this.add(infoLabel);
	}

	@Override
	public void addBetLabel() {
		//Do nothing
	}

	@Override
	public void addCardLabel() {
		cardPanel = CardPanelFactory.getInstance().makePanel();
		this.add(cardPanel);
	}

	public void loadFromPlayer(PlayerInterface p){
		this.player = p;
		try {
			if(player!=null){
				
				cardPanel.setTextContent(player.getHand(0));			
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
