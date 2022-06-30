package com.hearthstone.pojo;

public class Minion {
	
	private int health;
	private int attack;
	
	public Minion(com.hearthstone.Minion minion) {
		this.health = minion.getHealth();
		this.attack = minion.getAttack();
	}
	
}
