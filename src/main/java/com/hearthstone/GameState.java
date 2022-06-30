package com.hearthstone;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hearthstone.actions.Action;
import com.hearthstone.cards.Card;

public class GameState {
	
	private Player player1;
	private Player player2;
	private int turn; // 0表示player1, 1表示player2
	
	public GameState(Player player1, Player player2) {
		this.player1=player1;
		this.player2=player2;
	}
	
	
	public Player getOpPlayer(Player player) {
		if(this.player1==player) {
			return player2;
		} else {
			return player1;
		}
	}
		
	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}


	public List<Action> getActions() {
		
		List<Action> actions = new ArrayList<>();
		
		if(this.turn==player1.getTurn()) { // player1
			
			for(Card card : player1.getHandCards()) {
				// 费用足够
				if(card.getMana() <= player1.getMana()) {
					Action action = new Action(card, card.getTargets(this));
					actions.add(action);
				}
			}
		} else {
			for(Card card : player2.getHandCards()) {
				// 费用足够
				if(card.getMana() <= player2.getMana()) {
					Action action = new Action(card, card.getTargets(this));
					actions.add(action);
				}
			}
		}
		
		return actions;
		
	}
	
	public void takeAction(Action action) {
		action.take();		
	}
	
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); // pretty print
		com.hearthstone.pojo.GameState gameState = new com.hearthstone.pojo.GameState(this);
		return gson.toJson(gameState);
		
	}
}



