package com.hearthstone.cards.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.hearthstone.GameState;
import com.hearthstone.Minion;
import com.hearthstone.Player;
import com.hearthstone.actions.Target;
import com.hearthstone.cards.Card;


// 应该实现成一个集合解析，具备并交的能力
public class CardUtil {
	
	public static SortedSet<Target> parseTargets(Card card, String cardTargets, Map<String, Set<Target>> extraSetMap) {
		
		SortedSet<Target> targets = new TreeSet<>();
		
		GameState state = card.getPlayer().getGame().getGameState();
		Player opPlayer = state.getOpPlayer(card.getPlayer());
		
		// 鸟枪换炮了，利用set expression parser来稍微智能的解析集合
		
		Map<String, Set<Target>> setMap = new HashMap<>();
		
		// 1. my_minions
		SortedSet<Target> myMinions = new TreeSet<>();
		myMinions.addAll(card.getPlayer().getMinions());
		
		// 2. op_minions
		SortedSet<Target> opMinions = new TreeSet<>();
		opMinions.addAll(opPlayer.getMinions());
		
		// 3. my_dormant_minions
		SortedSet<Target> myDormantMinions = new TreeSet<>();
		for(Minion minion: card.getPlayer().getMinions()) {
			if(minion.isDormant())
				myDormantMinions.add(minion);
		}
		
		// 4. op_dormant_minions
		SortedSet<Target> opDormantMinions = new TreeSet<>();
		for(Minion minion: opPlayer.getMinions()) {
			if(minion.isDormant())
				opDormantMinions.add(minion);
		}
		
		// 5. my_taunt_minions
		SortedSet<Target> myTauntMinions = new TreeSet<>();
		for(Minion minion: card.getPlayer().getMinions()) {
			if(minion.isTaunt())
				myTauntMinions.add(minion);
		}
		
		// 6. op_taunt_minions
		SortedSet<Target> opTauntMinions = new TreeSet<>();
		for(Minion minion: opPlayer.getMinions()) {
			if(minion.isTaunt())
				opTauntMinions.add(minion);
		}
		
		// 5. my_stealth_minions
		SortedSet<Target> myStealthMinions = new TreeSet<>();
		for(Minion minion: card.getPlayer().getMinions()) {
			if(minion.isStealth())
				myStealthMinions.add(minion);
		}
		
		// 6. op_stealth_minions
		SortedSet<Target> opStealthMinions = new TreeSet<>();
		for(Minion minion: opPlayer.getMinions()) {
			if(minion.isStealth())
				opStealthMinions.add(minion);
		}
		
		// 7. my_hero
		SortedSet<Target> myHero = new TreeSet<>();
		myHero.add(card.getPlayer().getHero());
		
		// 8. op_hero
		SortedSet<Target> opHero = new TreeSet<>();
		opHero.add(opPlayer.getHero());
		
		// 9. my_hand_cards
		SortedSet<Target> myHandCards = new TreeSet<>();
		myHandCards.addAll(card.getPlayer().getHandCards());
		
		// 10. op_hand_cards
		SortedSet<Target> opHandCards = new TreeSet<>();
		opHandCards.addAll(opPlayer.getHandCards());
		
		
		setMap.put("my_minions", myMinions);
		setMap.put("op_minions", opMinions);
		setMap.put("my_dormant_minions", myDormantMinions);
		setMap.put("op_dormant_minions", opDormantMinions);
		setMap.put("my_stealth_minions", myStealthMinions);
		setMap.put("op_stealth_minions", opStealthMinions);
		setMap.put("my_taunt_minions", myTauntMinions);
		setMap.put("op_taunt_minions", opTauntMinions);
		setMap.put("my_hero", myHero);
		setMap.put("op_hero", opHero);
		setMap.put("my_hand_cards", myHandCards);
		setMap.put("op_hand_cards", opHandCards);
		
		if(extraSetMap!=null) {
			setMap.putAll(extraSetMap);
		}
		
		return SetExpressionParser.parse(cardTargets, setMap);
		
	}
	
}






