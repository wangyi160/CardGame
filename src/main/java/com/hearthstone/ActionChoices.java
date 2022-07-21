package com.hearthstone;

import java.util.List;

import com.hearthstone.actions.Action;

public class ActionChoices {
	
	private Action action;
	private List<Integer> targetChoices;
	
	public ActionChoices(Action action, List<Integer> targetChoices) {
		this.action= action;
		this.targetChoices = targetChoices;
	}

	public Action getAction() {
		return action;
	}

	public List<Integer> getTargetChoices() {
		return targetChoices;
	}
	
	
}
