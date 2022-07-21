package com.hearthstone.cards;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import com.hearthstone.ActionChoices;
import com.hearthstone.Minion;
import com.hearthstone.Player;
import com.hearthstone.actions.EntityTarget;
import com.hearthstone.actions.Target;

public class SpellCard extends Card {

	protected int spellDamage;
	
	public SpellCard(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}
	
	public List<ActionChoices> play(SortedSet<Target> targets, List<Integer> targetChoices) {
		List<ActionChoices> ret = super.play(targets, targetChoices);
		return ret;
				
	}

}
