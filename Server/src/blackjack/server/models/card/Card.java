package blackjack.server.models.card;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 */

public abstract class Card {
	
	public final static int CLUBS = 0;
	public final static int SPADES = 1;
	public final static int DIAMONDS = 2;
	public final static int HEARTS = 3;
	public final static int JOKERS = 4;		//joker value will be 0
	
	public final static String IMAGE_FOLDER = "images/";
	
	/**
	 * 
	 */
	protected int suit;
	
	/**
	 * 
	 */
	protected int value;
	
	/**
	 * 
	 */
	public String toString(){
		
		String suitString;	// 
		switch(suit){
		case 1:	suitString = "Heart"; 	break;
		case 2:	suitString = "Diamond"; break;
		case 3:	suitString = "Club";	break;
		case 4: suitString = "Joker";	break;
		default: suitString = "Spader";
		}
		
		String valueString;
		switch(value){
		case 0: valueString = "";
		case 1: valueString = "Ace";	break;
		case 11: valueString = "Jack";	break;
		case 12: valueString = "Queen";	break;
		case 13: valueString = "King";	break;
		default: valueString = value + "";
		}
		return suitString + " " + valueString;
	}
	
	/**
	 * @
	 * @return String, a-b, return 0-0 if the card is not valued, which stands for a joker
	 */
	public String toNumericString(){
		return this.suit+"-"+this.value;
	}
	
	/**
	 * 
	 * @return BufferedImage, image for the card
	 * @throws IOException
	 */
	public BufferedImage getImage() throws IOException{
		// file name like images/1-10.gif: SPADE 10
		File imageFile = new File( IMAGE_FOLDER + toNumericString() + ".gif");

		BufferedImage img = ImageIO.read(imageFile);
		return img;
	}
	
	/**
	 * @return int suit
	 */
	public int getSuit(){
		return this.suit;
	}
}
