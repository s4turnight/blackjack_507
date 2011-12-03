package blackjack.client.gui;

import java.rmi.RemoteException;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import blackjack.remote.HandInterface;

public class CardPanel extends JPanel{
	
	private JTextArea ta;
	
	public CardPanel() throws RemoteException{
		
		ta = new JTextArea(5, 12);
		ta.setEditable(false);
		ta.setSize(200, 150);
		this.add(ta);
	}
	
	public void setTextContent(HandInterface hand) throws RemoteException{
		String content = "";
		if(hand!=null){
			LinkedList<int[]> list = hand.getCardsIntArray();
			for(int i=0; i<list.size(); i++){
				content += intArrayToString(list.get(i))+"\n";
			}
		}
		ta.setText(content);
	}
	
	public static String intArrayToString(int[] array){
		if(array[0]==-1 && array[1]==-1){
			return "Unknown card";
		}
		else{
			String suitString;	// 
			switch(array[0]){
			case 1:	suitString = "Heart"; 	break;
			case 2:	suitString = "Diamond"; break;
			case 3:	suitString = "Club";	break;
			case 4: suitString = "Joker";	break;
			default: suitString = "Spader";
			}
			
			String valueString;
			switch(array[1]){
			case 0: valueString = "";
			case 1: valueString = "Ace";	break;
			case 11: valueString = "Jack";	break;
			case 12: valueString = "Queen";	break;
			case 13: valueString = "King";	break;
			default: valueString = array[1] + "";
			}
			return suitString + " " + valueString;
		}
	}
}
