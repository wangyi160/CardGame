package com.hearthstone.heros;

import java.util.ArrayList;
import java.util.List;

import com.hearthstone.GameState;
import com.hearthstone.Hero;
import com.hearthstone.Minion;
import com.hearthstone.Player;
import com.hearthstone.actions.Target;

public class Mage extends Hero
{
	private int powerAttack;
	
	public Mage(Player player) {
		super(player);
		this.powerAttack = 1;
		
		this.name = "法师";
	}

	public int getPowerAttack() {
		return powerAttack;
	}

	public void setPowerAttack(int powerAttack) {
		this.powerAttack = powerAttack;
	}
	
	public List<Target> getPowerTargets() {
		
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
	
	
}









