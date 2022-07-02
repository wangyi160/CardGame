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
	private int turn; // 回合数，从0开始
	
	private boolean gameOver;
	
	public GameState(Player player1, Player player2) {
		this.player1=player1;
		this.player2=player2;
		
		this.gameOver = false;
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
	
	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}
		

	public boolean isGameOver() {
		return gameOver;
	}


	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}


	public List<Action> getActions() {
		
		List<Action> actions = new ArrayList<>();
		
		System.out.println("game turn:"+this.turn);
		
		if(this.turn%2==player1.getTurn()) { // player1
			
			// 判断是否可以使用card
			for(Card card : player1.getHandCards()) {
				// 费用足够
				if(card.getMana() <= player1.getMana() && !(card.isMustHasTarget() && card.getTargets().size() < card.getTargetCount()) ) {
					
					Action action = new Action(card, card.getTargets(), "card");
					actions.add(action);
				}
			}
			
			// 判断是否可以使用英雄技能
			if(player1.getHero().canUsePower()) {
				
				Action action = new Action(player1.getHero(), player1.getHero().getPowerTargets(), "power");
				actions.add(action);
			}
			
			//  判断hero是否可以攻击
			
			if(player1.getHero().canAttack()) {
				Action action = new Action(player1.getHero(), player1.getHero().getTargets(), "attack");
				actions.add(action);
			}
			
			
			//  判断minions是否可以攻击
			for(Minion minion: player1.getMinions()) {
				if(minion.canAttack()) {
					Action action = new Action(minion, minion.getTargets(), "attack");
					actions.add(action);
				}
			}
			
		} else {
			
			// 判断是否可以使用card
			for(Card card : player2.getHandCards()) {
				// 费用足够
				if(card.getMana() <= player2.getMana()) {
					Action action = new Action(card, card.getTargets(), "card");
					actions.add(action);
				}
			}
			
			// 判断是否可以使用英雄技能
			if(player2.getHero().canUsePower()) {
				Action action = new Action(player2.getHero(), player2.getHero().getPowerTargets(), "power");
				actions.add(action);
			}
			
			//  判断hero是否可以攻击
			
			if(player2.getHero().canAttack()) {
				Action action = new Action(player2.getHero(), player2.getHero().getTargets(), "attack");
				actions.add(action);
			}
			
			
			//  判断minions是否可以攻击
			for(Minion minion: player2.getMinions()) {
				if(minion.canAttack()) {
					Action action = new Action(minion, minion.getTargets(), "attack");
					actions.add(action);
				}
			}
		}
		
		return actions;
		
	}
	
	public void takeAction(Action action, List<Integer> targetChoices) {
		action.take(targetChoices);	
		
		// 处理hero
		if(this.player1.getHero().getHealth()<=0) {
			this.gameOver=true;
		}
		
		if(this.player2.getHero().getHealth()<=0) {
			this.gameOver=true;
		}
		
		// 处理minions
		for(int i=this.player1.getMinions().size()-1;i>=0;i--) {
			
			Minion minion = this.player1.getMinions().get(i);
			if(minion.getHealth()<=0) {
				this.player1.getMinions().remove(i);
			}
		}
		
		for(int i=this.player2.getMinions().size()-1;i>=0;i--) {
			
			Minion minion = this.player2.getMinions().get(i);
			if(minion.getHealth()<=0) {
				this.player2.getMinions().remove(i);
			}
		}
		
		
	}
	
	
	
	public void beginTurn() {
		
		System.out.println("开始回合:"+this.turn);
		
		
		if(this.turn%2==player1.getTurn()) { // player1
			
			// 将本方随从的round增1， 重置attackcount
			for(Minion minion: player1.getMinions()) {
				minion.setRound(minion.getRound()+1);
				minion.resetAttackCount();
			}
			
			// 将本方hero重置attackcount
			this.player1.getHero().resetAttackCount();
			
			// 将本方player的mana数设置为turn/2+1
			this.player1.setMana(this.turn/2+1);
			
		} else {
			// 将本方随从的round增1， 重置attackcount
			for(Minion minion: player2.getMinions()) {
				minion.setRound(minion.getRound()+1);
				minion.resetAttackCount();
			}
			
			// 将本方hero重置attackcount
			this.player2.getHero().resetAttackCount();
			
			// 将本方player的mana数设置为turn/2+1
			this.player2.setMana(this.turn/2+1);
		}
		
		
	}
	
	public void endTurn() {
		System.out.println("结束回合:"+this.turn);
		this.turn++;
	}
	
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); // pretty print
		com.hearthstone.pojo.GameState gameState = new com.hearthstone.pojo.GameState(this);
		return gson.toJson(gameState);
		
	}
}



