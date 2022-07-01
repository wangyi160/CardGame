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
		
		System.out.println("game turn:"+this.turn);
		
		if(this.turn==player1.getTurn()) { // player1
			
			// 判断是否可以使用card
			for(Card card : player1.getHandCards()) {
				// 费用足够
				if(card.getMana() <= player1.getMana()) {
					Action action = new Action(card, card.getTargets());
					actions.add(action);
				}
			}
			
			// 判断是否可以使用英雄技能
			if(player1.getHero().canUsePower()) {
				System.out.println(player1.getHero());
				Action action = new Action(player1.getHero(), player1.getHero().getPowerTargets());
				actions.add(action);
			}
			
			//  判断hero是否可以攻击
			
			if(player1.getHero().canAttack()) {
				Action action = new Action(player1.getHero(), player1.getHero().getTargets());
				actions.add(action);
			}
			
			
			//  判断minions是否可以攻击
			for(Minion minion: player1.getMinions()) {
				if(minion.canAttack()) {
					Action action = new Action(minion, minion.getTargets());
					actions.add(action);
				}
			}
			
		} else {
			
			// 判断是否可以使用card
			for(Card card : player2.getHandCards()) {
				// 费用足够
				if(card.getMana() <= player2.getMana()) {
					Action action = new Action(card, card.getTargets());
					actions.add(action);
				}
			}
			
			// 判断是否可以使用英雄技能
			if(player2.getHero().canUsePower()) {
				Action action = new Action(player2.getHero(), player2.getHero().getPowerTargets());
				actions.add(action);
			}
			
			//  判断hero是否可以攻击
			
			if(player2.getHero().canAttack()) {
				Action action = new Action(player2.getHero(), player2.getHero().getTargets());
				actions.add(action);
			}
			
			
			//  判断minions是否可以攻击
			for(Minion minion: player2.getMinions()) {
				if(minion.canAttack()) {
					Action action = new Action(minion, minion.getTargets());
					actions.add(action);
				}
			}
		}
		
		return actions;
		
	}
	
	public void takeAction(Action action) {
		action.take();		
	}
	
	public void beginTurn() {
		
		System.out.println("开始回合");
		
		// 将本方随从的round增1
		if(this.turn==player1.getTurn()) { // player1
			for(Minion minion: player1.getMinions()) {
				minion.setRound(minion.getRound()+1);
			}
		} else {
			for(Minion minion: player2.getMinions()) {
				minion.setRound(minion.getRound()+1);
			}
		}
	}
	
	public void endTurn() {
		System.out.println("结束回合");
		this.turn = this.turn==0? 1:0;
	}
	
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); // pretty print
		com.hearthstone.pojo.GameState gameState = new com.hearthstone.pojo.GameState(this);
		return gson.toJson(gameState);
		
	}
}



