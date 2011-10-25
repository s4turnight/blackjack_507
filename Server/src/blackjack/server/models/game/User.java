package blackjack.server.models.game;

public class User {
	
	public User(int id, String name){
		this.id = id;
		this.name = name;
	}
	int id;
	String name;
	int amount;
}
