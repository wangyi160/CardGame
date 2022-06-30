package com.hearthstone;

import com.hearthstone.actions.Target;
import com.hearthstone.cards.Card;

public class Minion implements Target {
	
	private Card card;
	private int health;
	private int attack;
	
	public Minion(Card card) {
		this.card = card;
		this.health = this.card.getHealth();
		this.attack = this.card.getAttack();
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
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
	
	
	
}
