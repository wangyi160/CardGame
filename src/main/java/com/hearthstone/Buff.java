package com.hearthstone;


// buff是指一次性的加成
public class Buff {

	private int attackAdd;
	private int healthAdd;
	private int attackSet;
	private int healthSet;
	
	// buff持续的轮数
	private int round;
		
	private boolean immune;
	
	public Buff() {
		
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

	public int getAttackSet() {
		return attackSet;
	}

	public void setAttackSet(int attackSet) {
		this.attackSet = attackSet;
	}

	public int getHealthSet() {
		return healthSet;
	}

	public void setHealthSet(int healthSet) {
		this.healthSet = healthSet;
	}

	public boolean isImmune() {
		return immune;
	}

	public void setImmune(boolean immune) {
		this.immune = immune;
	}
	
	
	
}
