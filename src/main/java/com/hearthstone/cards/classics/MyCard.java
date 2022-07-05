package com.hearthstone.cards.classics;

import java.util.ArrayList;
import java.util.List;

import com.hearthstone.GameState;
import com.hearthstone.Minion;
import com.hearthstone.Player;

import com.hearthstone.actions.Target;
import com.hearthstone.cards.AuraTrait;
import com.hearthstone.cards.Card;
import com.hearthstone.cards.ChargeTrait;
import com.hearthstone.cards.MinionCard;
import com.hearthstone.cards.RushTrait;
import com.hearthstone.cards.TauntTrait;

import com.hearthstone.Aura;

public class MyCard extends MinionCard implements  TauntTrait, ChargeTrait, AuraTrait
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
	}
	
	public List<Target> getMinionTargets(Minion minion) {
		List<Target> targets = new ArrayList<>();
		
		if(this instanceof ChargeTrait || minion.getRound() > 0) {
		
			// 作为一个案例，将对手的英雄和所有的minion都加入到targets中
			GameState state = this.player.getGame().getGameState();
			Player opPlayer = state.getOpPlayer(this.player);
					
			targets.add(opPlayer.getHero());
			for(Minion opMinion: state.getOpPlayer(this.player).getMinions()) {
				targets.add(opMinion);
			}
		} 
		
		
		return targets;
	}
	
	public List<Target> getAuraTargets(Minion source) {
		
		
		List<Target> targets = new ArrayList<>();
				
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
	
	public void play(List<Target> targets, List<Integer> targetChoices) {
		
		super.play(targets, targetChoices);
		
		System.out.println("执行play");
				
		// 生成一个minion
		
		if(this instanceof MinionCard) {
			minion = new Minion(this);
			this.player.addMinion(minion);
			
			
		}
		
		// 战吼
		if(this instanceof MinionCard)
			battleCry(minion);
		
		// 冲锋
		if(this instanceof ChargeTrait)
			charge(minion);
		
		// 光环
		if(this instanceof AuraTrait) {
			aura(minion);
		}
		
		// 减掉player的费用
		this.player.setMana(this.player.getMana() - this.mana);
		
	}
	
	public void battleCry(Minion minion) {
		
	}
	
	public void charge(Minion minion) {
		minion.resetAttackCount();
	}

	// 这个是设置自身的aura
	public void aura(Minion minion) {
		// TODO Auto-generated method stub
		
		Aura aura = new Aura(minion);
		
		aura.setAttackAdd(1);
		aura.setHealthAdd(1);
		
		minion.setAura(aura);
		
//		// 找到需要aura的targets，给她们aura
//		for(Target target: this.getAuraTargets(minion)) {
//			target.addAura(aura);
//		}
		
		
	}

	
}





