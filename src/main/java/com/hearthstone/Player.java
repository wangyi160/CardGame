package com.hearthstone;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hearthstone.cards.Card;
import com.hearthstone.heros.Mage;
import com.hearthstone.heros.Warrior;

public class Player {
	
	private List<Card> handCards;
	private List<Card> deckCards;
	
	private int mana;
	
	private List<Minion> minions;
	private Hero hero;
	
	// 我的轮次
	private int turn;
	
	private Game game;
	
	public Player(Game game, String name) {
		this.handCards = new ArrayList<>();
		this.deckCards = new ArrayList<>();
		this.minions = new ArrayList<>();
		
		switch(name) {
		case "mage":	
			this.hero = new Mage(this);
			break;
		case "warrior":
			this.hero = new Warrior(this);
			break;
		}
		
		this.game = game;
	}
	
	

	public List<Card> getHandCards() {
		return handCards;
	}

	public void setHandCards(List<Card> handCards) {
		this.handCards = handCards;
	}

	public List<Card> getDeckCards() {
		return deckCards;
	}

	public void setDeckCards(List<Card> deckCards) {
		this.deckCards = deckCards;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public List<Minion> getMinions() {
		return minions;
	}

	public void setMinions(List<Minion> minions) {
		this.minions = minions;
	}
	
	public void addMinion(Minion minion) {
		this.minions.add(minion);
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	
	public Hero getHero() {
		return hero;
	}

	
	public Game getGame() {
		return game;
	}
	
	

	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); // pretty print
		com.hearthstone.pojo.Player pojoPlayer = new com.hearthstone.pojo.Player(this);
		return gson.toJson(pojoPlayer);
	}
	
	// 一些有用的helper函数，用于测试
	
	// 直接给牌
	public void giveCard(Card card) {
		this.handCards.add(card);
	}
	
	// 直接移除第n个随从，不会产生其他效果
	public void removeMinion(int index) {
		
		if(this.minions.size()>index) {
			this.minions.remove(index);
		}
		
	}
	
}













