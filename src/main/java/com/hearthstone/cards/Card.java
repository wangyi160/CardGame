package com.hearthstone.cards;

import java.util.ArrayList;
import java.util.List;

import com.hearthstone.GameState;
import com.hearthstone.Player;
import com.hearthstone.actions.Source;
import com.hearthstone.actions.Target;

public class Card implements Source 
{
	protected int mana;
	protected int health;
	protected int attack;
	
	protected Player player;
	
	public Card(Player player) {
		this.player = player;
	}
	
	public void play() {
		
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}
	
	public List<Target> getTargets(GameState state) {
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
	
	
	
}
