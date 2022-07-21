package com.hearthstone.listeners;

import java.util.ArrayList;
import java.util.List;

import com.hearthstone.ActionChoices;
import com.hearthstone.Player;
import com.hearthstone.actions.Action;
import com.hearthstone.actions.Source;

public class DrawHandler extends ActionListener 
{
	
	public DrawHandler(Source source) {
		super(source);
	}
	
	@Override
	public List<ActionChoices> handleAfter(Action action) {
		// TODO Auto-generated method stub
		
		if(action.getType().equals("draw")) {
			
		}
		
		return new ArrayList<>();
		
	}

}
