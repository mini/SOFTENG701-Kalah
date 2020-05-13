package kalah.game.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private int numPlayers;
	private int housesPerPlayer;
	private List<List<Store>> playersContainers = new ArrayList<>();
	private List<Store> gameBoard = new ArrayList<>();

	public Board(int numPlayers, int housesPerPlayer, int initialSeeds) {
		this.numPlayers = numPlayers;
		this.housesPerPlayer = housesPerPlayer;

		for (int i = 0; i < numPlayers; numPlayers++) {
			List<Store> playerContianers = new ArrayList<>();
			for (int j = 0; j < housesPerPlayer; j++) {
				playerContianers.add(new House(initialSeeds));
			}
			playerContianers.add(new Store());
			playersContainers.add(playerContianers);
			gameBoard.addAll(playerContianers);
		}

	}

	public int getPlayerScore(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getWinner() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumPlayers() {
		return numPlayers;
	}

	public int getHousesPerPlayer() {
		return housesPerPlayer;
	}

}
