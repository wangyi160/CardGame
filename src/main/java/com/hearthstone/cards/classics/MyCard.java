package com.hearthstone.cards.classics;

import java.util.ArrayList;
import java.util.List;

import com.hearthstone.GameState;
import com.hearthstone.Minion;
import com.hearthstone.Player;
import com.hearthstone.actions.Target;
import com.hearthstone.cards.Card;
import com.hearthstone.cards.Charge;
import com.hearthstone.cards.Rush;
import com.hearthstone.cards.Taunt;

public class MyCard extends Card implements Taunt, Rush, Charge
{
	public MyCard(Player player) {
		super(player);
		
		this.health = 1;
		this.attack = 1;
		this.mana = 2;
	}
	
	public List<Target> getTargets(GameState state) {
		List<Target> targets = new ArrayList<>();
		
		// 作为一个案例，将对手的所有的minion都加入到targets中
		for(Minion minion: state.getOpPlayer(this.player).getMinions()) {
			targets.add(minion);
		}
		
		return targets;
	} 
	
	public void play() {
						
		// 生成一个minion
		Minion minion = new Minion(this);
		this.player.addMinion(minion);
		
		// 减掉player的费用
		this.player.setMana(this.player.getMana() - this.mana);
		
	}
	
}





