package com.hearthstone.cards.classics;

import java.util.ArrayList;
import java.util.List;

import com.hearthstone.GameState;
import com.hearthstone.Minion;
import com.hearthstone.Player;
import com.hearthstone.actions.Target;
import com.hearthstone.cards.Card;
import com.hearthstone.cards.ChargeCard;
import com.hearthstone.cards.MinionCard;
import com.hearthstone.cards.RushCard;
import com.hearthstone.cards.TauntCard;

public class MyCard extends Card implements MinionCard, TauntCard, ChargeCard
{
	public MyCard(Player player) {
		super(player);
		
		this.health = 1;
		this.attack = 1;
		this.mana = 2;
		
		this.name= "冲锋猪";
	}
	
	public List<Target> getMinionTargets(Minion minion) {
		List<Target> targets = new ArrayList<>();
		
		if(this instanceof ChargeCard || minion.getRound() > 0) {
		
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
	
	public void play() {
		
		super.play();
		
		Minion minion = null;
		
		// 生成一个minion
		
		if(this instanceof MinionCard) {
			minion = new Minion(this);
			this.player.addMinion(minion);
		}
		
		// 战吼
		if(this instanceof MinionCard)
			battleCry(minion);
		
		// 冲锋
		if(this instanceof MinionCard)
			charge(minion);
		
		// 减掉player的费用
		this.player.setMana(this.player.getMana() - this.mana);
		
	}
	
	public void battleCry(Minion minion) {
		
	}
	
	public void charge(Minion minion) {
		minion.setCharge(true);
	}

	
}





