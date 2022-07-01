package com.hearthstone;

import java.util.ArrayList;
import java.util.List;

import com.hearthstone.actions.Source;
import com.hearthstone.actions.Target;

public class Hero implements Source, Target 
{
	
	protected Player player;
	
	protected int mana;
	protected int attack;
	protected int health;
	protected int armor;
	
	protected String name;
	
	public Hero(Player player) {
		this.player = player;
				
		this.health = 30;
		this.attack = 0;
		
		this.mana = 2;
		this.armor = 0;
		
	}
	
	
	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Target> getTargets() {
		
		List<Target> targets = new ArrayList<>();
		
		// 将对手的英雄和所有的minion都加入到targets中
		GameState state = this.player.getGame().getGameState();
		Player opPlayer = state.getOpPlayer(this.player);
				
		targets.add(opPlayer.getHero());
		for(Minion minion: state.getOpPlayer(this.player).getMinions()) {
			targets.add(minion);
		}
		
		return targets;
				
	}
	
	public List<Target> getPowerTargets() {
		return new ArrayList<Target>();
	}
	
	public boolean canUsePower() {
		return this.mana <= this.player.getMana();
	}
	
	public boolean canAttack() {
		if( this.attack > 0 && this.getTargets().size() > 0 ) {
			return true;
		}
		
		return false;
	}
	
}
