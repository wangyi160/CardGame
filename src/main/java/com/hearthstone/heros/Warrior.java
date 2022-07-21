package com.hearthstone.heros;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import com.hearthstone.ActionChoices;
import com.hearthstone.Hero;
import com.hearthstone.Player;
import com.hearthstone.actions.Action;
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
	
	public List<ActionChoices> heroPower(SortedSet<Target> targets, List<Integer> targetChoices) {
		
		this.armor += this.armorAdd;
		this.player.setMana(this.player.getMana() - this.mana);
		this.remainingPowerCount--;
		
		List<ActionChoices> ret = new ArrayList<>();
		
		Action action = new Action(this, targets, "heropower");
		ActionChoices ac = new ActionChoices(action, targetChoices);
		ret.add(ac);
		
		return ret;
	}
	
		
}
