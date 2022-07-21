package com.hearthstone;

import com.hearthstone.actions.EntitySource;

// aura是指持续性的加成，直到source死亡或沉默
public class Aura {
	private int attackAdd; // 给auratargets加攻击力
	private int healthAdd; // 给auratargets加生命
	
	private int damageAdd; // 法强
	
	private EntitySource source;
	
	public Aura(EntitySource source ) {
		this.source = source;
	}

	public int getAttackAdd() {
		return attackAdd;
	}

	public void setAttackAdd(int attackAdd) {
		this.attackAdd = attackAdd;
	}

	public int getHealthAdd() {
		return healthAdd;
	}

	public void setHealthAdd(int healthAdd) {
		this.healthAdd = healthAdd;
	}

	public EntitySource getSource() {
		return source;
	}

	public void setSource(EntitySource source) {
		this.source = source;
	}

	public int getDamageAdd() {
		return damageAdd;
	}

	public void setDamageAdd(int damageAdd) {
		this.damageAdd = damageAdd;
	}
	
	
	
}
