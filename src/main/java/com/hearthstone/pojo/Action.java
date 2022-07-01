package com.hearthstone.pojo;

import java.util.ArrayList;
import java.util.List;

public class Action {
	private Source source;
	private List<Target> targets;
	
	public Action(com.hearthstone.actions.Action action) {
		
		if(action.getSource() instanceof com.hearthstone.cards.Card) {
			this.source = new Card((com.hearthstone.cards.Card)action.getSource());
		}
		else if(action.getSource() instanceof com.hearthstone.Hero) {
			this.source = new Hero( (com.hearthstone.Hero)action.getSource() );
		}
		else if(action.getSource() instanceof com.hearthstone.Minion) {
			this.source = new Minion( (com.hearthstone.Minion)action.getSource() );
		}
		
		this.targets = new ArrayList<>();
		
		for(com.hearthstone.actions.Target target: action.getTargets()) {
			
			if(target instanceof com.hearthstone.cards.Card) {
				this.targets.add(new Card((com.hearthstone.cards.Card)target));
			}
			else if(target instanceof com.hearthstone.Hero) {
				this.targets.add(new Hero( (com.hearthstone.Hero)target ));
			}
			else if(target instanceof com.hearthstone.Minion) {
				this.targets.add(new Minion( (com.hearthstone.Minion)target ));
			}
			
		}
		
		
	}
}
