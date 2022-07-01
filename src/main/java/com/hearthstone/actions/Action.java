package com.hearthstone.actions;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
	
	

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public List<Target> getTargets() {
		return targets;
	}

	public void setTargets(List<Target> targets) {
		this.targets = targets;
	}
	
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); // pretty print
		com.hearthstone.pojo.Action pojoAction = new com.hearthstone.pojo.Action(this);
		return gson.toJson(pojoAction);
	}
	
}
