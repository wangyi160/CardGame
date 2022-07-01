package com.hearthstone;

import java.util.List;

import com.hearthstone.actions.Action;
import com.hearthstone.cards.classics.MyCard;

public class Game {

	private GameState state;
	
	public Game() {
		
	}
	
	public void start() {
		
		Player player1 = new Player(this, "mage");
		Player player2 = new Player(this, "warrior");
		
		player1.setMana(10);
		player2.setMana(10);
		
		player1.setTurn(0);
		player2.setTurn(1);
		
		player1.giveCard(new MyCard(player1));
		player2.giveCard(new MyCard(player2));
		
		
		
		this.state = new GameState(player1, player2);
		System.out.println(state);
		
		// 1. begin turn
		state.beginTurn();
		
		
		// 2. player1 take action (play card)
		List<Action> actions = state.getActions();
		System.out.println(actions.size());
		
		for(Action action: actions) {
			System.out.println(action);
		}
		
		state.takeAction(actions.get(0));
		
		// 3. player2 take action (play card)
		
//		state.endTurn();
		
		actions = state.getActions();
		System.out.println(actions.size());
		
		for(Action action: actions) {
			System.out.println(action);
		}
//		
////		state.takeAction(actions.get(0));
//		
//		// 4. end turn
//		state.endTurn();
//		
//		System.out.println(state);
		
		
	}
	
	public GameState getGameState() {
		return this.state;
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
}
