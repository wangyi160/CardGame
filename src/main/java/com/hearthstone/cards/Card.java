package com.hearthstone.cards;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.hearthstone.ActionChoices;
import com.hearthstone.Aura;
import com.hearthstone.Buff;
import com.hearthstone.GameState;
import com.hearthstone.Minion;
import com.hearthstone.Player;
import com.hearthstone.actions.CardSource;
import com.hearthstone.actions.CardTarget;
import com.hearthstone.actions.EntitySource;
import com.hearthstone.actions.Target;

public class Card implements CardSource, CardTarget
{
	protected int mana;
	
	protected String name;
	
	protected boolean mustHasTarget;
	protected int targetCount;
	
	protected Player player;
	
//	protected int damage; // 对于法术牌，这个是直接造成的伤害，对于随从牌，这个是战吼造成的伤害
	
	// 接收的auras
	protected Map<EntitySource, Aura> auras;
	
	public Card(Player player) {
		this.player = player;
	}
	
	public List<ActionChoices> play(SortedSet<Target> targets, List<Integer> targetChoices) {
		
		// 从手牌中删除
		this.player.getHandCards().remove(this);
				
		// 减掉player的费用
		this.player.setMana(this.player.getMana() - this.mana);
		
		return new ArrayList<>();
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}
	
	public SortedSet<Target> getTargets() {
		SortedSet<Target> targets = new TreeSet<>();
		return targets;
	}
	
	
	
	public SortedSet<Target> getAuraTargets(Minion minion) {
		
		SortedSet<Target> targets = new TreeSet<>();
		return targets;
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
	
	

	

	public boolean isMustHasTarget() {
		return mustHasTarget;
	}

	public void setMustHasTarget(boolean mustHasTarget) {
		this.mustHasTarget = mustHasTarget;
	}

	public int getTargetCount() {
		return targetCount;
	}

	public void setTargetCount(int targetCount) {
		this.targetCount = targetCount;
	}

	@Override
	public void removeAura(EntitySource source) {
		Aura aura = this.auras.remove(source);		
	}

	@Override
	public void addBuff(Buff buff) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAura(Aura aura) {
		// TODO Auto-generated method stub
		if(!this.auras.containsKey(aura.getSource())) {
			this.auras.put(aura.getSource(), aura);
		}
	}

//	@Override
//	public void addAura(Aura aura) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void addBuff(Buff buff) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public int getRemainingHealth() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	
//	@Override
//	public void removeAura(EntitySource source) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void causeDamage(int damage) {
//		// TODO Auto-generated method stub
//		
//	}
	
}











