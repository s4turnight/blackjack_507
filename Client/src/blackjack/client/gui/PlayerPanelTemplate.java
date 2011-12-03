package blackjack.client.gui;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import blackjack.remote.PlayerInterface;

public abstract class PlayerPanelTemplate extends JPanel{
	
	protected PlayerInterface player;
	protected CardPanel cardPanel;
	protected JLabel infoLabel;
	protected JLabel betLabel;
	
	public abstract void initialize();
	public abstract void addInfoPanel();
	public abstract void addBetLabel();
	public abstract void addCardLabel();
	public abstract void loadFromPlayer(PlayerInterface p);
	
	public PlayerPanelTemplate(PlayerInterface player){
		this.player = player;
		addComponents();
	}
	
//	@Override
//	protected void paintComponent(Graphics g){
//		super.paintComponent(g);
//		addComponents();
//	}
	
	public final void addComponents(){
		initialize();
		addInfoPanel();
		addBetLabel();
		addCardLabel();
	}
}
