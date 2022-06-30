package com.hearthstone;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hearthstone.cards.Card;

public class Player {

	private List<Card> handCards;
	private List<Card> deckCards;
	
	private int mana;
	
	private List<Minion> minions;
	
	// 我的轮次
	private int turn;
	
	public Player() {
		this.handCards = new ArrayList<>();
		this.deckCards = new ArrayList<>();
		this.minions = new ArrayList<>();
	}
	
	public void giveCard(Card card) {
		this.handCards.add(card);
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
	
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); // pretty print
		com.hearthstone.pojo.Player pojoPlayer = new com.hearthstone.pojo.Player(this);
		return gson.toJson(pojoPlayer);
	}
	
	
}
