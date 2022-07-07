package com.hearthstone.heros;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hearthstone.GameState;
import com.hearthstone.Hero;
import com.hearthstone.Minion;
import com.hearthstone.Player;
import com.hearthstone.actions.EntityTarget;
import com.hearthstone.actions.Target;

public class Mage extends Hero
{
	private int powerAttack;
	
	public Mage(Player player) {
		super(player);
		this.powerAttack = 1;
		
		this.name = "法师";
	}

	public int getPowerAttack() {
		return powerAttack;
	}

	public void setPowerAttack(int powerAttack) {
		this.powerAttack = powerAttack;
	}
	
	public Set<Target> getPowerTargets() {
		
		Set<Target> targets = new HashSet<>();
		
		// 将对手的英雄和所有的minion都加入到targets中
		GameState state = this.player.getGame().getGameState();
		Player opPlayer = state.getOpPlayer(this.player);
				
		targets.add(opPlayer.getHero());
		for(Minion minion: state.getOpPlayer(this.player).getMinions()) {
			targets.add(minion);
		}
		
		return targets;
				
	}
	
	public void heroPower(List<Target> targets, List<Integer> targetChoices) {
		
						
		Target target = targets.get(targetChoices.get(0));
		
		if(target instanceof Hero) {
			Hero hero =(Hero) target;
			
			int armorAttack = hero.getArmor()>=this.powerAttack? this.powerAttack: hero.getArmor();
			int healthAttack = this.powerAttack - armorAttack;
			
			hero.setArmor(hero.getArmor() - armorAttack);
			hero.causeDamage( healthAttack);
			
		}
		else if(target instanceof Minion) {
			Minion minion = (Minion)target;
			minion.causeDamage(this.powerAttack);
		}
				
		this.player.setMana(this.player.getMana() - this.mana);
		this.remainingPowerCount--;
	}
	
	
	
}









