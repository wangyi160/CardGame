package com.hearthstone.cards;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import com.hearthstone.ActionChoices;
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
	protected boolean aura;
	
	protected boolean dormant;
	protected int dormantRound;
	
	protected boolean cannotAttack;
	
	protected boolean stealth;
	
	protected int cryDamage;
	
	
	public MinionCard(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}
	
	public List<ActionChoices> play(SortedSet<Target> targets, List<Integer> targetChoices) {
		List<ActionChoices> ret = super.play(targets, targetChoices);
		
		// 生成一个minion
				
		minion = new Minion((MinionCard)this);
		this.player.addMinion(minion);		
		
		
		// 战吼
		ret.addAll(battleCry(minion, targets, targetChoices));
					
		// 光环
		if(this.aura) {
			aura(minion);
		}
		
		return ret;
		
	}
	
	public List<ActionChoices> battleCry(Minion minion, SortedSet<Target> targets, List<Integer> targetChoices) {
		return new ArrayList<>();
	}
	
	// 亡语
	public void deathRattle(Minion minion) {
		
	}
	
	public void charge(Minion minion) {
		minion.resetAttackCount();
	}

	// 这个是设置自身的aura
	public void aura(Minion minion) {
		
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

	public boolean isAura() {
		return aura;
	}

	public void setAura(boolean aura) {
		this.aura = aura;
	}

	public int getDormantRound() {
		return dormantRound;
	}

	public void setDormantRound(int dormantRound) {
		this.dormantRound = dormantRound;
	}

	public boolean isCannotAttack() {
		return cannotAttack;
	}

	public void setCannotAttack(boolean cannotAttack) {
		this.cannotAttack = cannotAttack;
	}

	public boolean isStealth() {
		return stealth;
	}

	public void setStealth(boolean stealth) {
		this.stealth = stealth;
	}

	
	
}












