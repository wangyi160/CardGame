package com.hearthstone.actions;

public interface EntitySource extends Source{

	public void setHealth(int health);
	public int getHealth();
	public int getRemainingHealth();
	public void setAttack(int attack);
	public int getAttack();
	
}
