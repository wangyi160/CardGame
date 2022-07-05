package com.hearthstone.cards;

import java.util.List;

import com.hearthstone.Aura;
import com.hearthstone.Minion;
import com.hearthstone.Player;
import com.hearthstone.actions.Target;

public class MinionCard extends Card {

	// 如果生成了minion，记录在这里
	protected Minion minion;
	
	protected int health;
	protected int attack;
	protected int attackCount;
	
	public MinionCard(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}
	
	public void play(List<Target> targets, List<Integer> targetChoices) {
		super.play(targets, targetChoices);
		
		// 生成一个minion
				
		minion = new Minion((MinionCard)this);
		this.player.addMinion(minion);		
		
		
		// 战吼
		battleCry(minion);
		
		// 冲锋
		if(this instanceof ChargeTrait)
			charge(minion);
		
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
	
}
