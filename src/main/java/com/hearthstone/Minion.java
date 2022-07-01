package com.hearthstone;

import java.util.ArrayList;
import java.util.List;

import com.hearthstone.actions.Source;
import com.hearthstone.actions.Target;
import com.hearthstone.cards.Card;

public class Minion implements Source, Target {
	
	private Card card;
	private int health;
	private int attack;
	private String name;
	
	private boolean cannotAttack;
	
	private boolean charge;
	
	// 上场的第几轮
	private int round;
	
	public Minion(Card card) {
		this.card = card;
		this.health = this.card.getHealth();
		this.attack = this.card.getAttack();
		this.name = this.card.getName();
		
		this.round= 0 ;
		this.cannotAttack = false;
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

	public boolean isCharge() {
		return charge;
	}

	public void setCharge(boolean charge) {
		this.charge = charge;
	}
	
	
	public boolean isCannotAttack() {
		return cannotAttack;
	}

	public void setCannotAttack(boolean cannotAttack) {
		this.cannotAttack = cannotAttack;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// 继承了card的minion targets
	public List<Target> getTargets() {
		return this.card.getMinionTargets(this);
	} 
	
	public boolean canAttack() {
		if( !this.cannotAttack && this.attack > 0 && this.getTargets().size() > 0 ) {
			return true;
		}
		
		return false;
	}
	
}













