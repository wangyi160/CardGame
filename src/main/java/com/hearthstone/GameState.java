package com.hearthstone;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hearthstone.actions.Action;
import com.hearthstone.actions.EntityTarget;
import com.hearthstone.actions.Target;
import com.hearthstone.cards.Card;
import com.hearthstone.listeners.ActionListener;

public class GameState {
	
	private Player player1;
	private Player player2;
	private int turn; // 回合数，从0开始
	
	private boolean gameOver;
	
	private List<ActionChoices> acs;
	
	public GameState(Player player1, Player player2) {
		this.player1=player1;
		this.player2=player2;
		
		this.gameOver = false;
		
		this.acs = new ArrayList<>();
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
	
	private List<ActionChoices> preAction(Action action) {
		List<ActionChoices> ret = new ArrayList<>();
		
		// 触发player的actionListeners, 调用handleBefore
		
		for(ActionListener listener: this.player1.getListeners()) {
			ret.addAll(listener.handleBefore(action));
		}
		
		for(ActionListener listener: this.player2.getListeners()) {
			ret.addAll(listener.handleBefore(action));
		}
		
		// 触发minion的actionListeners
		for(int i=0;i<this.player1.getMinions().size();i++) {
			
			Minion minion = this.player1.getMinions().get(i);
			for(ActionListener listener: minion.getListeners()) {
				ret.addAll(listener.handleBefore(action));
			}
		}
		
		for(int i=0;i<this.player2.getMinions().size();i++) {
			
			Minion minion = this.player2.getMinions().get(i);
			for(ActionListener listener: minion.getListeners()) {
				ret.addAll(listener.handleBefore(action));
			}
		}
		
		return ret;
		
	}
	
	private List<ActionChoices> postAction(Action action) {
		
		List<ActionChoices> ret = new ArrayList<>();
		
		// 触发player的actionListeners, 调用handleAfter
		for(ActionListener listener: this.player1.getListeners()) {
			ret.addAll(listener.handleAfter(action));
		}
		
		for(ActionListener listener: this.player2.getListeners()) {
			ret.addAll(listener.handleAfter(action));
		}
		
		// 触发minion的actionListeners
		for(int i=0;i<this.player1.getMinions().size();i++) {
			
			Minion minion = this.player1.getMinions().get(i);
			for(ActionListener listener: minion.getListeners()) {
				ret.addAll(listener.handleAfter(action));
			}
		}
		
		for(int i=0;i<this.player2.getMinions().size();i++) {
			
			Minion minion = this.player2.getMinions().get(i);
			for(ActionListener listener: minion.getListeners()) {
				ret.addAll(listener.handleAfter(action));
			}
		}
		
		// 处理hero死亡
		if(this.player1.getHero().getRemainingHealth()<=0) {
			this.gameOver=true;
		}
		
		if(this.player2.getHero().getRemainingHealth()<=0) {
			this.gameOver=true;
		}

		
		
		// 处理minions死亡
		for(int i=this.player1.getMinions().size()-1;i>=0;i--) {
			
			Minion minion = this.player1.getMinions().get(i);
			if(minion.getRemainingHealth()<=0) {
				
				// 从该minion的auratargets中移除
				for(Target target: minion.getAuraTargets()) {
					target.removeAura(minion);
				}
				
				// 如果该minion在player或minion中引起actionlistener，将其移除
				removeListeners(minion);
				
				// 从player的minions列表中移除
				this.player1.getMinions().remove(i);
			}
		}
		
		for(int i=this.player2.getMinions().size()-1;i>=0;i--) {
			
			Minion minion = this.player2.getMinions().get(i);
			if(minion.getRemainingHealth()<=0) {

				// 从该minion的auratargets中移除
				for(Target target: minion.getAuraTargets()) {
					target.removeAura(minion);
				}
				
				// 如果该minion在player或minion中引起actionlistener，将其移除
				removeListeners(minion);

				this.player2.getMinions().remove(i);
			}
		}
		
		// 处理auras
				
		for(int i=0; i<this.player1.getMinions().size();i++) {
			
			Minion minion = this.player1.getMinions().get(i);
			
			if(minion.getAura()!=null) {
				
				for(Target target: minion.getAuraTargets()) {
					target.addAura(minion.getAura());
				}
				
			}
		}
		
		for(int i=0; i<this.player2.getMinions().size();i++) {
			
			Minion minion = this.player2.getMinions().get(i);
			
			if(minion.getAura()!=null) {
				
				for(Target target: minion.getAuraTargets()) {
					target.addAura(minion.getAura());
				}
				
			}
		}
		
		return ret;
		
		
	}
	
	private void removeListeners(Minion minion) {
		// 触发player的actionListeners, 调用handleBefore
		
		for(int i=this.player1.getListeners().size()-1; i>=0; i--) {
			ActionListener listener = this.player1.getListeners().get(i);
			if(listener.getSource() == minion) {
				this.player1.getListeners().remove(i);
			}
		}
		
		for(int i=this.player2.getListeners().size()-1; i>=0; i--) {
			ActionListener listener = this.player2.getListeners().get(i);
			if(listener.getSource() == minion) {
				this.player2.getListeners().remove(i);
			}
		}
		
		// 触发minion的actionListeners
		for(int i=0;i<this.player1.getMinions().size();i++) {
			
			Minion m = this.player1.getMinions().get(i);
			for(int j = m.getListeners().size()-1; j>=0; j--) {
				ActionListener listener = m.getListeners().get(j);
				if(listener.getSource() == minion) {
					m.getListeners().remove(j);
				}
			}
		}
		
		for(int i=0;i<this.player2.getMinions().size();i++) {
			
			Minion m = this.player2.getMinions().get(i);
			for(int j = m.getListeners().size()-1; j>=0; j--) {
				ActionListener listener = m.getListeners().get(j);
				if(listener.getSource() == minion) {
					m.getListeners().remove(j);
				}
			}
		}
		
	}
	
	// 有可能一个action会牵连出其他的action，因此这里处理的应该是一个列表
	public void takeAction(Action action, List<Integer> targetChoices) {
		this.acs.add(new ActionChoices(action, targetChoices));
		
		while(this.acs.size()>0) {
			ActionChoices ac = this.acs.remove(0);
			this.takeAction(ac);
		}
		
	}
	
	// 执行某一个特定的action
	public void takeAction(ActionChoices ac) {
		
		// 有可能action被阻止掉
		List<ActionChoices> preAcs = preAction(ac.getAction());
		this.acs.addAll(preAcs);
		
		// 实际进行action
		List<ActionChoices> takeAcs =  ac.getAction().take(ac.getTargetChoices());	
		this.acs.addAll(takeAcs);
		
		// action造成的效果进行处理
		List<ActionChoices> postAcs = postAction(ac.getAction());
		this.acs.addAll(postAcs);
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
			this.player1.getHero().resetPowerCount();
			
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
			this.player2.getHero().resetPowerCount();
			
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



