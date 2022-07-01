package com.hearthstone.pojo;

public class Hero implements Source, Target
{
	
	private int mana;
	private int attack;
	private int health;
	private int armor;
	private String name;
	
	public Hero(com.hearthstone.Hero hero) {
		
		this.mana = hero.getMana();
		this.attack = hero.getAttack();
		this.health = hero.getHealth();
		this.armor = hero.getArmor();
		
		this.name = hero.getName();
		
	}
}
