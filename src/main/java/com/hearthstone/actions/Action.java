package com.hearthstone.actions;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hearthstone.ActionChoices;
import com.hearthstone.Hero;
import com.hearthstone.Minion;
import com.hearthstone.Player;
import com.hearthstone.cards.Card;
import com.hearthstone.heros.Mage;
import com.hearthstone.heros.Warrior;

public class Action {

	private Source source;
	private SortedSet<Target> targets;
	
	private String type;
	
	public Action(Source source, SortedSet<Target> targets, String type) {
		
		this.source = source;
		this.targets = targets;
		
		this.type = type;
		
	}
	
	// 由一个action引发的惨案
	public List<ActionChoices> take(List<Integer> targetChoices) {
		
		if( this.source instanceof Card) { // type == card
			Card card =(Card)this.source;
			return card.play(targets, targetChoices);
		}
		else if(this.source instanceof Minion) { // type == attack
			Minion minion =(Minion)this.source;
			
			assert targetChoices.size() == 1;
						
			return minion.attack( targets.iterator().next());
		}
		
		else if(this.source instanceof Hero && this.type.equals("attack")) { 
			Hero hero =(Hero)this.source;
			
			assert targetChoices.size() == 1;
			return hero.attack(targets.iterator().next());
		}
		else if(this.source instanceof Hero && this.type.equals("power")) {
			Hero hero =(Hero)this.source;
			
			assert targetChoices.size() == 0;
			return hero.heroPower(targets, targetChoices);
		}
		else if(this.type.equals("draw")) {
			Player player = (Player)this.source;
			player.draw();
			return new ArrayList<>();
		}
		
		return new ArrayList<>();
		
	}
	
	public int getChoiceCount() {
		
		if(this.source instanceof Card) {
			Card card =(Card)this.source;
			
			if(card.isMustHasTarget())
				return card.getTargetCount();
			else if(card.getTargets().size()>=card.getTargetCount())
				return card.getTargetCount();
			else
				return 0;
		}
		else if(this.source instanceof Minion) {
			return 1;
		}
		else if(this.source instanceof Mage) {
			return 1;
		}
		
		return 0;
	}
		

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public Set<Target> getTargets() {
		return targets;
	}

	public void setTargets(SortedSet<Target> targets) {
		this.targets = targets;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); // pretty print
		com.hearthstone.pojo.Action pojoAction = new com.hearthstone.pojo.Action(this);
		return gson.toJson(pojoAction);
	}
	
}
