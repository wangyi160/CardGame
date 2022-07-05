package com.hearthstone.pojo;

public class Minion implements Source, Target
{
	
	private int health;
	private int attack;
			
	private int round;
	private String name;
	
	private int remainingHealth;
	
	
	private String playerName;
	
	public Minion(com.hearthstone.Minion minion) {
		this.health = minion.getHealth();
		this.attack = minion.getAttack();
		this.name = minion.getName();
		this.round = minion.getRound();
		
		this.remainingHealth = minion.getRemainingHealth();
		this.playerName = minion.getCard().getPlayer().getHero().getName();
	
		
	}
	
	
}
