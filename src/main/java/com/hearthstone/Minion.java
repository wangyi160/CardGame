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
	
	// 每轮的攻击次数
	private int attackCount;
	
	// 当前剩余的攻击次数
	private int remainingAttackCount;
	
	private String name;
	
	private boolean cannotAttack;
	
	
	// 上场的第几轮
	private int round;
	
	public Minion(Card card) {
		this.card = card;
		this.health = this.card.getHealth();
		this.attack = this.card.getAttack();
		this.attackCount = this.card.getAttackCount();
				
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
	
	public int getAttackCount() {
		return attackCount;
	}

	public void setAttackCount(int attackCount) {
		this.attackCount = attackCount;
	}
	
	public void resetAttackCount() {
		this.remainingAttackCount = this.attackCount;
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
		if( !this.cannotAttack && this.attack > 0 && this.getTargets().size() > 0 && this.remainingAttackCount > 0 ) {
			return true;
		}
		
		return false;
	}
	
	public void attack(Target target) {
		
		// TODO 要考虑各种状态
		if(target instanceof Hero) {
			Hero hero =(Hero) target;
			
			int armorAttack = hero.getArmor()>=this.attack? this.attack: hero.getArmor();
			int healthAttack = this.attack - armorAttack;
			
			hero.setArmor(hero.getArmor() - armorAttack);
			hero.setHealth(hero.getHealth() - healthAttack);
			
		}
		else if(target instanceof Minion) {
			target.setHealth(target.getHealth() - this.attack);
			this.setHealth(this.health - target.getAttack());
		}
		
		this.remainingAttackCount --;
	}
	
}













