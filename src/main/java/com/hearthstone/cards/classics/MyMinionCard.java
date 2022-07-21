package com.hearthstone.cards.classics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.hearthstone.GameState;
import com.hearthstone.Minion;
import com.hearthstone.Player;
import com.hearthstone.actions.Action;
import com.hearthstone.actions.Target;
import com.hearthstone.cards.AuraTrait;
import com.hearthstone.cards.Card;

import com.hearthstone.cards.MinionCard;
import com.hearthstone.cards.util.CardUtil;
import com.hearthstone.ActionChoices;
import com.hearthstone.Aura;

public class MyMinionCard extends MinionCard 
{
	public MyMinionCard(Player player) {
		super(player);
		
		this.health = 1;
		this.attack = 1;
		this.attackCount = 1;
		this.mana = 2;
		
		this.name= "冲锋猪";
		
		this.mustHasTarget = false;
		this.targetCount = 0;
		
		this.charge = true;
		this.taunt = true;
		this.aura = true;
	}
	
	
	
	public SortedSet<Target> getAuraTargets(Minion source) {
				
		SortedSet<Target> targets = new TreeSet<>();
		SortedSet<Target> myMinions = CardUtil.parseTargets(this, "my_minions|my_hand_cards", null);
		
		// 去掉自己
		for(Target target: myMinions) {
			if(target!=source) {
				targets.add(minion);
			}
		}
		
		return targets;
	}
	
	public List<ActionChoices> play(SortedSet<Target> targets, List<Integer> targetChoices) {
		List<ActionChoices> ret = super.play(targets, targetChoices);
		return ret;
	}
	
	
	public void aura(Minion minion) {
		// TODO Auto-generated method stub
		Aura aura = new Aura(minion);
		
		aura.setAttackAdd(1);
		aura.setHealthAdd(1);
		aura.setDamageAdd(1); // 法强+1
		
		minion.setAura(aura);
		
	}
	
	public void deathRattle(Minion minon) {
		
		// 抽一张牌
		Action action = new Action(this.player, null, "draw");
		this.player.getGame().getGameState().takeAction(action, null);
		
	}

	
}





