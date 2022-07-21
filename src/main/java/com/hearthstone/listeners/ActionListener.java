package com.hearthstone.listeners;

import java.util.List;

import com.hearthstone.ActionChoices;
import com.hearthstone.actions.Action;
import com.hearthstone.actions.Source;

public class ActionListener {
	
	protected Source source;
	
	public ActionListener(Source source) {
		this.source = source;
	}
	
	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public List<ActionChoices> handleBefore(Action action) { return null; }
	public List<ActionChoices> handleAfter(Action action) { return null; }
}
