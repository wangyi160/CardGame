package com.hearthstone.cards.classics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hearthstone.GameState;
import com.hearthstone.Minion;
import com.hearthstone.Player;

import com.hearthstone.actions.Target;
import com.hearthstone.cards.AuraTrait;
import com.hearthstone.cards.Card;

import com.hearthstone.cards.MinionCard;

import com.hearthstone.Aura;

public class MyCard extends MinionCard 
{
	public MyCard(Player player) {
		super(player);
		
		this.health = 1;
		this.attack = 1;
		this.attackCount = 1;
		this.mana = 2;
		
		this.name= "冲锋猪";
		
		this.mustHasTarget = false;
		this.targetCount = 0;
		
		this.charge = true;
		this.taunt = true;
	}
	
	
	
	public Set<Target> getAuraTargets(Minion source) {
				
		Set<Target> targets = new HashSet<>();
				
		// 作为一个案例，将本方的minion都加入到targets中，这里不包括自己
		for(Minion minion: this.player.getMinions()) {
			
			if(minion!=source) {
				
				System.out.println("找到aura targets");
				System.out.println(minion);
				System.out.println(source);
				
				targets.add(minion);

			}
		}
		
		return targets;
	}
	
	public void play(Set<Target> targets, List<Integer> targetChoices) {
		
		super.play(targets, targetChoices);
		
	}
	
	

	
}





