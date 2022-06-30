package com.hearthstone;

import java.util.List;

import com.hearthstone.actions.Action;
import com.hearthstone.cards.classics.MyCard;

public class Game {

	private List<GameState> states;
	
	public Game() {
		
	}
	
	public void start() {
		
		Player player1 = new Player();
		Player player2 = new Player();
		
		player1.setMana(10);
		player2.setMana(10);
		
		player1.setTurn(0);
		player2.setTurn(1);
		
		player1.giveCard(new MyCard(player1));
		player2.giveCard(new MyCard(player2));
		
		GameState state = new GameState(player1, player2);
		System.out.println(state);
		
		List<Action> actions = state.getActions();
		System.out.println(actions.size());
		
		state.takeAction(actions.get(0));
		
		System.out.println(state);
		
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
}
