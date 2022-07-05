package com.hearthstone.cards;

import java.util.ArrayList;
import java.util.List;

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
	
	
	
	public Card(Player player) {
		this.player = player;
	}
	
	public void play(List<Target> targets, List<Integer> targetChoices) {
		
		// 从手牌中删除
		this.player.getHandCards().remove(this);
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}
	
	public List<Target> getTargets() {
		List<Target> targets = new ArrayList<>();
		return targets;
	}
	
	public List<Target> getMinionTargets(Minion minion) {
		
		List<Target> targets = new ArrayList<>();
		return targets;
	}
	
	public List<Target> getAuraTargets(Minion minion) {
		
		List<Target> targets = new ArrayList<>();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBuff(Buff buff) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAura(Aura aura) {
		// TODO Auto-generated method stub
		
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











