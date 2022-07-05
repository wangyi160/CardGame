package com.hearthstone.pojo;

public class SpellCard extends Card implements Source, Target  
{
	private int mana;
	private String name;
	
	public SpellCard(com.hearthstone.cards.SpellCard card)  {
		
		this.mana = card.getMana();
		this.name = card.getName();
	}
	
}
