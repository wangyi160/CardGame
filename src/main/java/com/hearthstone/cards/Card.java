package com.hearthstone.cards;

import java.util.ArrayList;
import java.util.List;

import com.hearthstone.GameState;
import com.hearthstone.Minion;
import com.hearthstone.Player;
import com.hearthstone.actions.Source;
import com.hearthstone.actions.Target;

public class Card implements Source, Target
{
	protected int mana;
	protected int health;
	protected int attack;
	protected String name;
	
	protected Player player;
	
	public Card(Player player) {
		this.player = player;
	}
	
	public void play() {
		
		// 从手牌中删除
		this.player.getHandCards().remove(this);
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}
	
	public List<Target> getTargets() {
		List<Target> targets = new ArrayList<>();
		return targets;
	}
	
	public List<Target> getMinionTargets(Minion minion) {
		List<Target> targets = new ArrayList<>();
		return targets;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}











