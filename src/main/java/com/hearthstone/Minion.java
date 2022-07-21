package com.hearthstone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.hearthstone.actions.Action;
import com.hearthstone.actions.EntitySource;
import com.hearthstone.actions.EntityTarget;
import com.hearthstone.actions.Source;
import com.hearthstone.actions.Target;
import com.hearthstone.cards.Card;

import com.hearthstone.cards.MinionCard;

import com.hearthstone.cards.util.CardUtil;
import com.hearthstone.listeners.ActionListener;

public class Minion implements EntitySource, EntityTarget {
	
	private MinionCard card;
	private int baseHealth; // 基础血量，来自于卡牌内部
	private int baseAttack;
	
	private int health; // 当前基础剩余血量
	private int attack;
	
	private int remainingHealth; // 当前剩余血量，可能被各种buff过
	
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
	
	private boolean charge;
	private boolean rush;
	private boolean taunt;
	private boolean dormant;
	private boolean stealth;
	
	// 是否被沉默
	private boolean disabled;
	
	// 上场的第几轮
	private int round;
	
	// 事件监听列表，对于action
	private List<ActionListener> listeners;
	
	public Minion(MinionCard card) {
		this.card = card;
		this.baseHealth = this.card.getHealth();
		this.baseAttack = this.card.getAttack();
		this.attackCount = this.card.getAttackCount();
				
		this.name = this.card.getName();
		
		this.round= 0 ;
		this.cannotAttack = this.card.isCannotAttack();
				
		this.auras = new HashMap<>();
		
		
		
		this.health = this.baseHealth;
		this.attack = this.baseAttack;
		
		this.remainingHealth = this.health;
		
		// 几大属性
		this.charge = this.card.isCharge();
		this.rush = this.card.isRush();
		this.taunt = this.card.isTaunt();
		this.dormant = this.card.isDormant();
		this.stealth = this.card.isStealth();
		
		this.disabled = false;
		
		this.listeners = new ArrayList<>();
	}
	
	// 向minion添加listener
	public void addActionListener(ActionListener listener) {
		this.listeners.add(listener);
	}
	
	// 删除listener，根据source，删除
	public void removeActionListener(Source source) {
		for(int i=this.listeners.size()-1; i>=0; i--) {
			if(this.listeners.get(i).getSource()==source) {
				this.listeners.remove(i);
			}
		}
	}
		

	public List<ActionListener> getListeners() {
		return listeners;
	}

	

	public boolean isCharge() {
		return this.charge;
	}

	public boolean isRush() {
		return this.rush;
	}

	public boolean isTaunt() {
		return this.taunt;
	}
	
	public boolean isDormant() {
		return dormant;
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
	public List<ActionChoices> causeDamage(int damage) {
		
		List<ActionChoices> ret = new ArrayList<>();
			
		this.remainingHealth -= damage;
		
		Action action = new Action(this, null, "damage");
		ActionChoices ac = new ActionChoices(action, null);
		ret.add(ac);
		
		return ret;
				
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

	// 获取攻击目标，由于后期的charge等状态可能有所改变，所以不再继承card
	public SortedSet<Target> getTargets() {
		SortedSet<Target> targets = new TreeSet<>();
		
		// 处理冲锋
		if(this.charge ) {
			targets.addAll(CardUtil.parseTargets(this.card, "(op_minions-op_dormant_minions)|op_hero", null));
			
		}
		
		// 处理突袭
		if(this.rush ) {
			targets.addAll(CardUtil.parseTargets(this.card, "op_minions-op_dormant_minions", null));
		}
		
		// 判断轮数
		if(this.getRound() > 0) {
			targets.addAll(CardUtil.parseTargets(this.card, "(op_minions-op_dormant_minions)|op_hero", null));
		}
		
		return targets;
	} 
		
	public boolean canAttack() {
		if( !this.cannotAttack && this.getAttack() > 0 && this.getTargets().size() > 0 && this.remainingAttackCount > 0 ) {
			return true;
		}
		
		return false;
	}
	
	public List<ActionChoices> attack(Target target) {
		
		List<ActionChoices> ret = new ArrayList<>();
		
		// TODO 要考虑各种状态
		if(target instanceof Hero) {
			Hero hero =(Hero) target;
			
			int armorAttack = hero.getArmor()>=this.getAttack()? this.getAttack(): hero.getArmor();
			int healthAttack = this.getAttack() - armorAttack;
			
			hero.setArmor(hero.getArmor() - armorAttack);
			ret.addAll( hero.causeDamage( healthAttack) );
			
		}
		else if(target instanceof Minion) {
			Minion minion = (Minion)target;
			
			ret.addAll( minion.causeDamage( this.getAttack()) );
			ret.addAll( this.causeDamage( minion.getAttack()) );
		}
		
		this.remainingAttackCount --;
		
		return ret;
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
		
		if(buff.getAttackSet()>0) {
			this.attack = buff.getAttackSet();
		}
		
		if(buff.getHealthAdd()>0) {
			this.health += buff.getHealthAdd();
			this.remainingHealth += buff.getHealthAdd();
		}
		
		if(buff.getHealthSet()>0) {
			this.health = buff.getHealthSet();
			
			// 需要重新计算remainingHealth
			
			this.remainingHealth = this.health;
			for(EntitySource source: this.auras.keySet()) {
				
				Aura aura = this.auras.get(source);
											
				if(aura.getHealthAdd()>0) {
					this.remainingHealth += aura.getHealthAdd();
				}
			}
			
		}
	}
	
	// 沉默
	public void disable() {
		this.attack = this.baseAttack;
		// 需要重新计算attack
		for(EntitySource source: this.auras.keySet()) {
			
			Aura aura = this.auras.get(source);
										
			if(aura.getAttackAdd()>0) {
				this.attack += aura.getAttackAdd();
			}
		}
		
		
		this.health = this.baseHealth;
		
		// 首先计算一下，health为满的状态下的fullHealth
		int fullHealth = this.health;
		for(EntitySource source: this.auras.keySet()) {
			
			Aura aura = this.auras.get(source);
										
			if(aura.getHealthAdd()>0) {
				fullHealth += aura.getAttackAdd();
			}
		}
		
		// 如果remainingHealth大于fullHealth，则变小
		if(this.remainingHealth > fullHealth)
			this.remainingHealth = fullHealth;
		
				
		// 从该minion的auratargets中移除aura
		for(Target target: this.getAuraTargets()) {
			target.removeAura(this);
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
	

	public boolean isStealth() {
		return stealth;
	}

	public Aura getAura() {
		return aura;
	}

	public void setAura(Aura aura) {
		this.aura = aura;
	}
	
	public Set<Target> getAuraTargets() {
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
	
	public void deathRattle() {
		this.card.deathRattle(this);
	}
}













