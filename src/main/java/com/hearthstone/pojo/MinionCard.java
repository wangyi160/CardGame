package com.hearthstone.pojo;

public class MinionCard extends Card implements Source, Target  
{
	private int mana;
	private int health;
	private int attack;
	private String name;
	
	public MinionCard(com.hearthstone.cards.MinionCard card)  {
		
		this.mana = card.getMana();
		this.health = card.getHealth();
		this.attack = card.getAttack();
		this.name = card.getName();
	}
	
}
