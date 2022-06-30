package com.hearthstone.pojo;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private int mana;
	private int turn;
	private List<Minion> minions;
	
	public Player( com.hearthstone.Player player ) {
		
		this.mana = player.getMana();
		this.turn = player.getTurn();
		
		this.minions = new ArrayList<>();
		
		for( com.hearthstone.Minion minion: player.getMinions()) {
			this.minions.add(new Minion(minion));
		}
		
	}
}


