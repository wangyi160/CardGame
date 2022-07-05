package com.hearthstone.cards;

import com.hearthstone.Minion;
import com.hearthstone.Player;

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
