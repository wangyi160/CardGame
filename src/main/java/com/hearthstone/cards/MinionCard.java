package com.hearthstone.cards;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hearthstone.Aura;
import com.hearthstone.GameState;
import com.hearthstone.Minion;
import com.hearthstone.Player;
import com.hearthstone.actions.Target;
import com.hearthstone.cards.util.CardUtil;

public class MinionCard extends Card {

	// 如果生成了minion，记录在这里
	protected Minion minion;
	
	protected int health;
	protected int attack;
	protected int attackCount;
	
	protected boolean charge;
	protected boolean rush;
	protected boolean taunt;
	protected boolean dormant;
	protected int dormantRound;
	
	public MinionCard(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}
	
	public void play(Set<Target> targets, List<Integer> targetChoices) {
		super.play(targets, targetChoices);
		
		// 生成一个minion
				
		minion = new Minion((MinionCard)this);
		this.player.addMinion(minion);		
		
		
		// 战吼
		battleCry(minion);
		
		
			
		// 光环
		if(this instanceof AuraTrait) {
			aura(minion);
		}
	}
	
	public void battleCry(Minion minion) {
		
	}
	
	public void charge(Minion minion) {
		minion.resetAttackCount();
	}

	// 这个是设置自身的aura
	public void aura(Minion minion) {
		// TODO Auto-generated method stub
		Aura aura = new Aura(minion);
		
		aura.setAttackAdd(1);
		aura.setHealthAdd(1);
		
		minion.setAura(aura);
		
	}
	
	
	public Minion getMinion() {
		return minion;
	}

	public void setMinion(Minion minion) {
		this.minion = minion;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
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
	
		
	public boolean isCharge() {
		return charge;
	}

	public void setCharge(boolean charge) {
		this.charge = charge;
	}

	public boolean isRush() {
		return rush;
	}

	public void setRush(boolean rush) {
		this.rush = rush;
	}

	public boolean isTaunt() {
		return taunt;
	}

	public void setTaunt(boolean taunt) {
		this.taunt = taunt;
	}

	public boolean isDormant() {
		return dormant;
	}

	public void setDormant(boolean dormant) {
		this.dormant = dormant;
	}

	
	
}












