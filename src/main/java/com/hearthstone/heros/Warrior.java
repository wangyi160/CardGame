package com.hearthstone.heros;

import java.util.List;

import com.hearthstone.Hero;
import com.hearthstone.Player;
import com.hearthstone.actions.EntityTarget;
import com.hearthstone.actions.Target;

public class Warrior extends Hero
{
	private int armorAdd;
	
	public Warrior(Player player) {
		super(player);
		
		this.name = "战士";
		this.armorAdd = 2;
	}
	
	public void heroPower(List<Target> targets, List<Integer> targetChoices) {
		
		this.armor += this.armorAdd;
		this.player.setMana(this.player.getMana() - this.mana);
		this.remainingPowerCount--;
	}
	
		
}
