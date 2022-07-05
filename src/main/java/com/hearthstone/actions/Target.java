package com.hearthstone.actions;

import com.hearthstone.Aura;
import com.hearthstone.Buff;

public interface Target {
	
	public void addBuff(Buff buff);
	
	public void addAura(Aura aura);
	public void removeAura(EntitySource source);
}
