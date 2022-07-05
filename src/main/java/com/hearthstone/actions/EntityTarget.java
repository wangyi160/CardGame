package com.hearthstone.actions;

import com.hearthstone.Aura;
import com.hearthstone.Buff;

public interface EntityTarget extends Target {
	public void setHealth(int health);
	public int getHealth();
	
	public void causeDamage(int damage);
	public int getRemainingHealth();
	
	public void setAttack(int attack);
	public int getAttack();
		
		
	
}
