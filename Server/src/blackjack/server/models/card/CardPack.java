package blackjack.server.models.card;

import java.util.Random;

/**
 * This cardset is for the use of blackjack, 4 decks, and jokers not included
 * @author Jiawei Jiang
 *
 */
public class CardPack {
	
	private Card[] cards;
	private int currentIndex;
	
	/**
	 * @modifies cards, current index
	 * @effects give values to cards array, set current index starting from 0
	 */
	public CardPack(int numberOfDecks){
		
		cards = new Card[numberOfDecks * 52];
		currentIndex = 0;
		
		int index = 0; 
		for(int k=0; k < numberOfDecks; k++ ){
			for(int i=0; i<4; i++){
				for(int j=1; j<14; j++){
					cards[index++] = new BlackJackCard(i,j);
				}
			}
		}
	}
	
	/**
	 * Method to shuffle the card set:
	 * Randomly select a card from the whole set, move it to the end, 
	 * then randomly select a card from the rest unmoved cards, move it to the second last position,
	 * Repeat this step till the last one, and finally set the current index to 0.
	 * 
	 * @requires cards initialized
	 * @modifies cards
	 * @effects makes all the element cards array in a random order, set current index starts from 0
	 */
	public void shuffle(){
		for(int i = cards.length - 1; i > 0; i--){	// i>0 because last move is not necessary
			Random r = new Random();
			int selected = r.nextInt(i+1);
			Card temp = cards[selected];
			for(int j = selected; j < i; j++){
				cards[j] = cards[j+1];
			}
			cards[i] = temp; 
		}
		currentIndex = 0;
	}
	
	/**
	 * @requires cards initialized
	 * @return
	 */
	public boolean hasNext(){
		return ( currentIndex < cards.length );
	}
	
	/**
	 * @requires cards initialized
	 * @return Card
	 * @modifies currentIndex
	 * @effects currentIndex++
	 */
	public Card next(){
		return cards[ currentIndex++ ];
	}
}
