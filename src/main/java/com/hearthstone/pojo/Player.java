package com.hearthstone.pojo;

import java.util.ArrayList;
import java.util.List;



public class Player {
	
	private int mana;
	private int turn;
	private List<Minion> minions;
	private List<Card> handCards;
	private Hero hero;
	
	public Player( com.hearthstone.Player player ) {
		
		this.mana = player.getMana();
		this.turn = player.getTurn();
		
		this.minions = new ArrayList<>();
		
		for( com.hearthstone.Minion minion: player.getMinions()) {
			this.minions.add(new Minion(minion));
		}
		
		this.handCards = new ArrayList<>();
		for( com.hearthstone.cards.Card card: player.getHandCards()) {
			
			if(card instanceof com.hearthstone.cards.MinionCard)
				this.handCards.add(new MinionCard( (com.hearthstone.cards.MinionCard)card));
			else 
				this.handCards.add(new SpellCard( (com.hearthstone.cards.SpellCard)card));
		}
		
		this.hero = new Hero(player.getHero());
		
	}
}


