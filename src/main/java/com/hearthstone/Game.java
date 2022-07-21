package com.hearthstone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hearthstone.actions.Action;
import com.hearthstone.cards.classics.MyMinionCard;

public class Game {

	private GameState state;
	
	public Game() {
		
	}
	
	public void start() {
		
		Player player1 = new Player(this, "mage");
		Player player2 = new Player(this, "warrior");
				
		player1.setTurn(0);
		player2.setTurn(1);
		
		player1.giveCard(new MyMinionCard(player1));
		player1.giveCard(new MyMinionCard(player1));
		
		player2.giveCard(new MyMinionCard(player2));
		player2.giveCard(new MyMinionCard(player2));
		
		
		
		this.state = new GameState(player1, player2);
		
		
		while(!state.isGameOver()) {
			
			
			
			// 1. begin turn
			state.beginTurn();
			
			System.out.println("gamestate:");
			System.out.println(state);
						
			while(true) {
								
				List<Action> actions = state.getActions();
				
				if(actions.size()==0) {
					break;
				}
				
				// 2. player1 take action (play card)
				System.out.println("actions:");
				for(int i=0;i < actions.size(); i++) {
					System.out.println(i);
					System.out.println(actions.get(i));
				}
				
				// 选择动作去执行
				System.out.println("which action to take:");
				Scanner cin = new Scanner(System.in);
				String line = cin.nextLine();
				
				if(line.equals("")) {
					break;
				}
				
				int actionChoice = Integer.parseInt(line);
				
				// 每个动作都有一个选择个数，普通怪物攻击是1，有的战吼可能是n>1，随机性的是0，全场打的是0
				
				List<Integer> targetChoices = new ArrayList<>();
				
				if( actions.get(actionChoice).getChoiceCount() > 0  ) {
					
					int i=0;
					while(actions.get(actionChoice).getTargets().iterator().hasNext()) {
						System.out.println(i);
						System.out.println(actions.get(actionChoice).getTargets().iterator().next());
						i++;
					}
					
					System.out.println("you have "+ actions.get(actionChoice).getChoiceCount() + " choices");
					
					// 选择目标去执行, 因为目标可能不唯一，所以是个数组
					
					for(i=0;i<actions.get(actionChoice).getChoiceCount();i++) {
						System.out.println("which target to choose:");
						String line2 = cin.nextLine();
						int targetChoice = Integer.parseInt(line2);
						targetChoices.add(targetChoice); 
					}
				}
				
				state.takeAction(actions.get(actionChoice), targetChoices);
				
				System.out.println("gamestate:");
				System.out.println(state);
			}
			
			// 3. end turn
			state.endTurn();
		}
		
		// 3. player2 take action (play card)
		
		
		
		
//		
////		state.takeAction(actions.get(0));
//		
//		// 4. end turn
//		state.endTurn();
//		
//		System.out.println(state);
		
		
	}
	
	public GameState getGameState() {
		return this.state;
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
}
