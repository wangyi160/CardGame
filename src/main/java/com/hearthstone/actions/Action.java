package com.hearthstone.actions;
import java.util.List;

import com.hearthstone.cards.Card;

public class Action {

	private Source source;
	private List<Target> targets;
	
	public Action(Source source, List<Target> targets) {
		
		this.source = source;
		this.targets = targets;
		
	}
	
	public void take() {
		
		if( this.source instanceof Card) {
			Card card =(Card)this.source;
			card.play();
		}
		
	}
}
