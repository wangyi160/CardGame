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
	protected int attackCount;
	protected int remainingAttackCount;
	protected int health;
	protected int armor;
	
	protected int powerCount;
	protected int remainingPowerCount;
	
	protected String name;
	
	public Hero(Player player) {
		this.player = player;
				
		this.health = 30;
		this.attack = 0;
		this.attackCount =1;
		
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
	

	public int getAttackCount() {
		return attackCount;
	}

	public void setAttackCount(int attackCount) {
		this.attackCount = attackCount;
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
	
	public void heroPower(List<Target> targets, List<Integer> targetChoices) {
		
	}
	
	public void attack(Target target) {
		
		// TODO 要考虑各种状态
		
		if(target instanceof Hero) {
			Hero hero =(Hero) target;
			
			int armorAttack = hero.getArmor()>=this.attack? this.attack: hero.getArmor();
			int healthAttack = this.attack - armorAttack;
			
			hero.setArmor(hero.getArmor() - armorAttack);
			hero.setHealth(hero.getHealth() - healthAttack);
			
		}
		else if(target instanceof Minion) {
			target.setHealth(target.getHealth() - this.attack);
			this.setHealth(this.health - target.getAttack());
		}
		
		this.remainingAttackCount --;
	}
	
	public void resetAttackCount() {
		this.remainingAttackCount = this.attackCount;
	}
	
}





