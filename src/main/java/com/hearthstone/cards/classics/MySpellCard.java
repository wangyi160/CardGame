package com.hearthstone.cards.classics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.hearthstone.GameState;
import com.hearthstone.Minion;
import com.hearthstone.Player;
import com.hearthstone.actions.EntitySource;
import com.hearthstone.actions.EntityTarget;
import com.hearthstone.actions.Target;
import com.hearthstone.cards.AuraTrait;
import com.hearthstone.cards.Card;

import com.hearthstone.cards.MinionCard;
import com.hearthstone.cards.SpellCard;
import com.hearthstone.ActionChoices;
import com.hearthstone.Aura;

public class MySpellCard extends SpellCard 
{
	public MySpellCard(Player player) {
		super(player);
				
		this.mana = 1;
		this.name= "奥术冲击";
		this.mustHasTarget = true;
		this.targetCount = 1;
		
	}
	
	
	
	public SortedSet<Target> getAuraTargets(Minion source) {
				
		SortedSet<Target> targets = new TreeSet<>();
				
		// 作为一个案例，将本方的cards都加入到targets中
		for(Card card: this.player.getHandCards()) {
			targets.add(card);

		}
		return targets;
	}
	
	public List<ActionChoices> play(SortedSet<Target> targets, List<Integer> targetChoices) {
		List<ActionChoices> ret = super.play(targets, targetChoices);
		
		// 造成伤害*2
		
		int damageAdd = 0;
		for(Map.Entry<EntitySource, Aura> entry: this.auras.entrySet()) {
			
			Aura aura = entry.getValue();
			if(aura.getDamageAdd()>0) {
				damageAdd+=aura.getDamageAdd();
			}
		}
		
		int realDamage = this.spellDamage + damageAdd*2 ;
		
		Target[] targetArr = (Target[])targets.toArray();
		
		for(int choice: targetChoices) {
			
			EntityTarget entityTarget = (EntityTarget)targetArr[choice];
			entityTarget.causeDamage(realDamage);
		}
		
		return ret;
				
	}
	
	
	public void aura(Minion minion) {
		// TODO Auto-generated method stub
		Aura aura = new Aura(minion);
		
		aura.setAttackAdd(1);
		aura.setHealthAdd(1);
		
		minion.setAura(aura);
		
	}

	
}





