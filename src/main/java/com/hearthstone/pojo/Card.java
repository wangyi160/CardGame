package com.hearthstone.pojo;

public class Card implements Source, Target 
{
	private int mana;
	private int health;
	private int attack;
	private String name;
	
	public Card(com.hearthstone.cards.Card card) {
		
		this.mana = card.getMana();
		this.health = card.getHealth();
		this.attack = card.getAttack();
		this.name = card.getName();
	}
	
}
