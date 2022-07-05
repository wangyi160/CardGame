package com.hearthstone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hearthstone.actions.EntitySource;
import com.hearthstone.actions.EntityTarget;
import com.hearthstone.actions.Target;

public class Hero implements EntitySource, EntityTarget 
{
	
	protected Player player;
	
	protected int mana;
	protected int attack;
	protected int attackCount;
	protected int remainingAttackCount;
	protected int health;
	protected int armor;
	
	protected int powerCount;
	protected int remainingPowerCount;
	
	protected int remainingHealth;
	
	
	protected String name;
	
			
	// auras
	private Map<EntitySource, Aura> auras;

	public Hero(Player player) {
		this.player = player;
				
		this.health = 30;
		this.attack = 0;
		this.attackCount =1;
		
		this.powerCount = 1;
				
		this.mana = 2;
		this.armor = 0;
		
		
		this.auras = new HashMap<>();
		
		this.remainingHealth = this.health;
	}
	
	
	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
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

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Target> getTargets() {
		
		List<Target> targets = new ArrayList<>();
		
		// 将对手的英雄和所有的minion都加入到targets中
		GameState state = this.player.getGame().getGameState();
		Player opPlayer = state.getOpPlayer(this.player);
				
		targets.add(opPlayer.getHero());
		for(Minion minion: state.getOpPlayer(this.player).getMinions()) {
			targets.add(minion);
		}
		
		return targets;
				
	}
	
	public List<Target> getPowerTargets() {
		return new ArrayList<Target>();
	}
	
	public boolean canUsePower() {
		return this.mana <= this.player.getMana() && this.remainingPowerCount>0;
	}
	
	public boolean canAttack() {
		if( this.getAttack() > 0 && this.getTargets().size() > 0 ) {
			return true;
		}
		
		return false;
	}
	
	public void heroPower(List<Target> targets, List<Integer> targetChoices) {
		
	}
	
	public void attack(Target target) {
		
		// TODO 要考虑各种状态
		
		if(target instanceof Hero) {
			Hero hero =(Hero) target;
			
			int armorAttack = hero.getArmor()>=this.getAttack()? this.getAttack(): hero.getArmor();
			int healthAttack = this.getAttack() - armorAttack;
			
			hero.setArmor(hero.getArmor() - armorAttack);
			hero.causeDamage(healthAttack);
			
		}
		else if(target instanceof Minion) {
			Minion minion = (Minion)target;
			minion.causeDamage(this.getAttack());
			this.causeDamage(minion.getAttack());
		}
		
		this.remainingAttackCount --;
	}
	
	public void resetAttackCount() {
		this.remainingAttackCount = this.attackCount;
	}
	
	public void resetPowerCount() {
		this.remainingPowerCount = this.powerCount;
	}


	@Override
	public void addAura(Aura aura) {
		// TODO Auto-generated method stub
		this.auras.put(aura.getSource(), aura);
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


	// 增加buff和aura的计算
	public int getRemainingHealth() {
				
		return this.remainingHealth;
	}

//	// 增加buff和aura的计算
//	public void setRemainingHealth(int health) {
//		
//		// 进行auras的逆计算
//		for(Source source: this.auras.keySet()) {
//			
//			Aura aura = this.auras.get(source);
//			
//			if(aura.getHealthAdd()>0) {
//				health -= aura.getHealthAdd();
//			}
//		}
//		
//		// 如此计算下来，本体health可能<=0，不过这也正常，后面在去掉buff的时候需要考虑把他加回来
//		this.remainingHealth = health;
//	}

	@Override
	public void removeAura(EntitySource source) {
		// TODO Auto-generated method stub
		Aura aura = this.auras.remove(source);
		
		
		// 消除Health效果，只减少超出health的部分
		if(aura.getHealthAdd()>0 && this.remainingHealth-aura.getHealthAdd()>=this.health) {
			 this.remainingHealth -= aura.getHealthAdd();
		}
		
		// 消除attack效果
		if(aura.getAttackAdd()>0) {
			this.attack -= aura.getAttackAdd();
		}
	}


	@Override
	public void causeDamage(int damage) {
		// TODO Auto-generated method stub
		this.remainingHealth -= damage;
	}
	


	
}





