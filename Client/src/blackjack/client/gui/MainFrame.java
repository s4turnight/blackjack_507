package blackjack.client.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import blackjack.remote.PlayerInterface;
import blackjack.remote.SessionInterface;
import blackjack.client.gui.PlayerPanelTemplate;

public class MainFrame extends JFrame{
	
	private PlayerInterface me;
	private JTextArea messageBox;
	private SessionInterface session;
	
	private ArrayList<PlayerPanelTemplate> playerPanels;
	private PlayerPanelTemplate dealerPanel;
	
	public MainFrame(PlayerInterface player, SessionInterface session){
		
		this.setSize(600, 500);
		me = player;
		this.session = session;
		
		playerPanels = new ArrayList<PlayerPanelTemplate>();
		
		draw();
		refresh(session, "");
	}
	
	public void draw(){
		try {
			this.setLayout(new BorderLayout());
			
			dealerPanel = new DealerPanel(session.getDealer());
			this.add(dealerPanel, BorderLayout.WEST);
			
			PlayerInterface[] players = session.getPlayers();
			
			JPanel playersPanel = new JPanel();
			playersPanel.setLayout(new GridLayout(3,1));
			this.add(playersPanel);
			if(players!=null){
				
				for(int i=0; i<players.length; i++){
	
					PlayerPanelTemplate ppt = new PlayerPanel(players[i]);
					playerPanels.add(ppt);
					playersPanel.add(ppt);
				}
			}
			
			messageBox = new JTextArea(12,20);
			this.add(messageBox, BorderLayout.EAST);
			
			this.add(new ButtonPanel(), BorderLayout.SOUTH);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
//		this.setVisible(true);
	}
	
	private class ButtonPanel extends JPanel{
		
		private ButtonPanel(){
			JButton hit = new JButton("hit");
			JButton start = new JButton("start");
			JButton stand = new JButton("stand");
			JButton doubleBT = new JButton("double");
			JButton clear = new JButton("clear");
			final JTextField betTF = new JTextField();
			betTF.setText("100");
			
			hit.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						me.hitAct();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			});
			
			start.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						me.start(Integer.parseInt(betTF.getText().trim()));
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			});
			
			stand.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						me.standAct();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			});
			
			doubleBT.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						me.doubleAct();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			});
			
			clear.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					messageBox.setText("");
				}
			});
			
			this.setLayout(new GridLayout(1,5));
			this.add(betTF);
			this.add(start);
			this.add(hit);
			this.add(stand);
			this.add(doubleBT);
		}
	}
	
	public void refresh(SessionInterface session, String message){
		try{
			dealerPanel.loadFromPlayer(session.getDealer());
			for(int i=0; i<playerPanels.size(); i++){
				playerPanels.get(i).loadFromPlayer(session.getPlayers()[i]);
			}
		}
		catch(RemoteException e){
			e.printStackTrace();
		}
		
		messageBox.append(message+"\n");
	}
}
