package com.hearthstone.cards.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hearthstone.GameState;
import com.hearthstone.Minion;
import com.hearthstone.Player;
import com.hearthstone.actions.Target;
import com.hearthstone.cards.Card;

public class CardUtil {
	
	public static Set<Target> parseTargets(Card card, String cardTargets) {
		
		Set<Target> targets = new HashSet<>();
		
		GameState state = card.getPlayer().getGame().getGameState();
		Player opPlayer = state.getOpPlayer(card.getPlayer());
		
		String[] parts = cardTargets.split("+");
		
		for(String part: parts) {
			
			if(part.equals("my_minions")) { 
				// 去掉dormant
				for(Minion minion: card.getPlayer().getMinions()) {
					
					if(!minion.isDormant())
						targets.add(minion);
				}
								
			}
			else if(part.equals("op_minions")) { // 无需考虑嘲讽
				// 去掉dormant
				for(Minion minion: opPlayer.getMinions()) {
					
					if(!minion.isDormant())
						targets.add(minion);
				}
			}
			else if(part.equals("op_attackable")) { // 需要考虑嘲讽，与下面不同是增加了英雄
				
				boolean hasTaunt = false;
				for(Minion minion: opPlayer.getMinions()) {
					if(minion.isTaunt() && !minion.isDormant())
						hasTaunt = true;
				}
				
				if(hasTaunt) {
					for(Minion minion: opPlayer.getMinions()) {
						if(minion.isTaunt() && !minion.isDormant())
							targets.add(minion);
					}
				} else {
					
					targets.add(opPlayer.getHero());
										
					for(Minion minion: opPlayer.getMinions()) {
						
						if(!minion.isDormant())
							targets.add(minion);
					}
				}
			}
			else if(part.equals("op_attackable_minions")) { // 需要考虑嘲讽
				
				boolean hasTaunt = false;
				for(Minion minion: opPlayer.getMinions()) {
					if(minion.isTaunt() && !minion.isDormant())
						hasTaunt = true;
				}
				
				if(hasTaunt) {
					for(Minion minion: opPlayer.getMinions()) {
						if(minion.isTaunt())
							targets.add(minion);
					}
				} else {
					for(Minion minion: opPlayer.getMinions()) {
						
						if(!minion.isDormant())
							targets.add(minion);
					}
				}
			}
			else if(part.equals("my_hero")) {
				targets.add(card.getPlayer().getHero());
			}
			else if(part.equals("op_hero")) {
				targets.add(opPlayer.getHero());
			}
			
		}
		
		
		return targets;
		
	}
	
}






