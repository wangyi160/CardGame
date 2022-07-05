package com.hearthstone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hearthstone.actions.EntitySource;
import com.hearthstone.actions.EntityTarget;
import com.hearthstone.actions.Target;
import com.hearthstone.cards.Card;
import com.hearthstone.cards.MinionCard;

public class Minion implements EntitySource, EntityTarget {
	
	private MinionCard card;
	private int baseHealth; // 基础血量，来自于卡牌内部
	private int baseAttack;
	
	private int health; // 当前血量，可能被各种buff过
	private int attack;
	
	private int remainingHealth; // 当前基础剩余血量
	
	// 每轮的攻击次数
	private int attackCount;
	
	// 当前剩余的攻击次数
	private int remainingAttackCount;
	
	private String name;
	
	private boolean cannotAttack;
	
	
	
	// 接收的auras
	private Map<EntitySource, Aura> auras;
	
	// 自身的aura
	private Aura aura;
	
	
	
	// 上场的第几轮
	private int round;
	
	public Minion(MinionCard card) {
		this.card = card;
		this.baseHealth = this.card.getHealth();
		this.baseAttack = this.card.getAttack();
		this.attackCount = this.card.getAttackCount();
				
		this.name = this.card.getName();
		
		this.round= 0 ;
		this.cannotAttack = false;
		
		
		this.auras = new HashMap<>();
		
		
		
		this.health = this.baseHealth;
		this.attack = this.baseAttack;
		
		this.remainingHealth = this.health;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(MinionCard card) {
		this.card = card;
	}

	// 增加buff和aura的计算
	public int getRemainingHealth() {
		return this.remainingHealth ;
	}

	// 增加buff和aura的计算
	public void causeDamage(int damage) {
				
		this.remainingHealth -= damage;
				
	}

	public int getAttack() {
		return this.attack;
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
		if( !this.cannotAttack && this.getAttack() > 0 && this.getTargets().size() > 0 && this.remainingAttackCount > 0 ) {
			return true;
		}
		
		return false;
	}
	
	public void attack(Target target) {
		
		// TODO 要考虑各种状态
		if(target instanceof Hero) {
			Hero hero =(Hero) target;
			
			int armorAttack = hero.getArmor()>=this.getAttack()? this.getAttack(): hero.getArmor();
			int healthAttack = this.getAttack() - armorAttack;
			
			hero.setArmor(hero.getArmor() - armorAttack);
			hero.causeDamage( healthAttack);
			
		}
		else if(target instanceof Minion) {
			Minion minion = (Minion)target;
			minion.causeDamage( this.getAttack());
			this.causeDamage( minion.getAttack());
		}
		
		this.remainingAttackCount --;
	}

	@Override
	public void addAura(Aura aura) {
				
		// TODO Auto-generated method stub
		
		if(!this.auras.containsKey(aura.getSource())) {
			this.auras.put(aura.getSource(), aura);
			
			// 更新remainingHealth
			if(aura.getHealthAdd()>0)
				this.remainingHealth+=aura.getHealthAdd();
			
			// 更新attack效果
			if(aura.getAttackAdd()>0) {
				this.attack += aura.getAttackAdd();
			}
		}
	}

	@Override
	public void addBuff(Buff buff) {
		// TODO Auto-generated method stub
		if(buff.getAttackAdd()>0) {
			this.attack+=buff.getAttackAdd();
		}
		else if(buff.getAttackSet()>0) {
			this.attack = buff.getAttackSet();
		}
		else if(buff.getHealthAdd()>0) {
			this.health += buff.getHealthAdd();
			this.remainingHealth += buff.getHealthAdd();
		}
		else if(buff.getHealthSet()>0) {
			this.health = buff.getHealthSet();
			
			// 需要重新计算remainingHealth
			
			this.remainingHealth = this.health;
			for(EntitySource source: this.auras.keySet()) {
				
				Aura aura = this.auras.get(source);
											
				if(aura.getHealthAdd()>0) {
					this.remainingHealth += aura.getAttackAdd();
				}
			}
			
		}
	}

	
	@Override
	public void setHealth(int health) {
		// TODO Auto-generated method stub
		this.health = health;
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return this.health;
	}

	public Aura getAura() {
		return aura;
	}

	public void setAura(Aura aura) {
		this.aura = aura;
	}
	
	public List<Target> getAuraTargets() {
		return this.card.getAuraTargets(this);
	}

	@Override
	public void removeAura(EntitySource source) {
		// TODO Auto-generated method stub
		Aura aura = this.auras.remove(source);
		
		// 消除Health效果，只减少超出health的部分
		if(aura.getHealthAdd()>0) {
			if(this.remainingHealth-aura.getHealthAdd()>=this.health) {
				this.remainingHealth -= aura.getHealthAdd();
			}
			else if(this.remainingHealth>=this.health)
			{
				this.remainingHealth=health;
			}
		}
		
		// 消除attack效果
		if(aura.getAttackAdd()>0) {
			this.attack -= aura.getAttackAdd();
		}
		
	}
}













