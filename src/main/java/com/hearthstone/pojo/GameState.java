package com.hearthstone.pojo;



public class GameState {

	private Player player1;
	private Player player2;
	private int turn; // 0表示player1, 1表示player2
	
	public GameState(com.hearthstone.GameState state) {
				
		this.player1 = new Player(state.getPlayer1());
		this.player2 = new Player(state.getPlayer2());
		this.turn = state.getTurn();
	}
	
}
