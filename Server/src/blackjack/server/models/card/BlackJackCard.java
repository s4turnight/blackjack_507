package blackjack.server.models.card;

/**
 * <p>
 * @author Jiawei Jiang
 * This class is used to create card objects.
 * Every card has an integer representing suit, an integer representing the card value.
 * Jokers not considered. 
 * Card ACE has two possible scores 1/11, we use 1 here. The score will be processed in the score counter.
 * </p> 
 */

public class BlackJackCard extends Card{
	
	/**
	 * 
	 * @param suit
	 * @param value
	 */
	public BlackJackCard(int suit, int value){
		// if parameters valid for a card
		if( suit>=0 && suit <=3 && value >= 1 && value <= 13){
			this.suit = suit;
			this.value = value;
		}
		else throw new IllegalArgumentException("Invalid Card");
	}
	
	public int getPoint(){
		if( value < 10 ){
			return value;
		}
		else return 10;
	}
}