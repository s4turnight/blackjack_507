package blackjack.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.rmi.RemoteException;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import blackjack.remote.PlayerInterface;

public class PlayerPanel extends PlayerPanelTemplate{

	public PlayerPanel(PlayerInterface player) {
		super(player);
	}

	@Override
	public void initialize() {
		
		this.setSize(200, 200);
	}

	@Override
	public void addInfoPanel() {
		
		infoLabel = new JLabel("Empty");
		this.add(infoLabel);
	}

	@Override
	public void addBetLabel() {
		betLabel = new JLabel("Not started");
		this.add(betLabel);
	}

	@Override
	public void addCardLabel() {
		cardPanel = CardPanelFactory.getInstance().makePanel();
		this.add(cardPanel);
	}

	@Override
	public void loadFromPlayer(PlayerInterface p) {
		
		this.player = p;
		
		try {
			// load player amount and name
			if(player==null)
				infoLabel.setText("Empty");
			else
			{	
				infoLabel.setText(player.getName()+": $"+player.getAmount());
				// load bet
				if(player.isStarted()){
					betLabel.setText("Bet: "+player.getHand(0).getBet());
				}
				else betLabel.setText("Not started");
				
				// load player cards
				cardPanel.setTextContent(player.getHand(0));
				
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
