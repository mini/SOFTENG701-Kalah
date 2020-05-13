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

		for (int i = 0; i < numPlayers; i++) {
			List<Store> playerContianers = new ArrayList<>();
			for (int j = 0; j < housesPerPlayer; j++) {
				playerContianers.add(new House(initialSeeds));
			}
			playerContianers.add(new Store());
			playersContainers.add(playerContianers);
			gameBoard.addAll(playerContianers);
		}

	}

	public int getPlayerScore(int playerNum) {
		int sum = 0;
		for (Store pit : playersContainers.get(playerNum - 1)) {
			sum += pit.getNumSeeds();
		}
		return sum;
	}

	public int getCurrentFirstPlace() {
		int bestScore = 0;
		int bestPlayer = 0;
		boolean tieExists = false;
		for(int i = 1; i <= numPlayers; i++) {
			int score = getPlayerScore(i);
			if(score > bestScore) {
				bestScore = score;
				bestPlayer = i;
				tieExists = false;
			} else if (score == bestScore) {
				tieExists = true;
			}
		}
		
		if(tieExists) {
			return 0;
		} else {
			return bestPlayer;
		}
	}

	public int getNumPlayers() {
		return numPlayers;
	}

	public int getHousesPerPlayer() {
		return housesPerPlayer;
	}

	public Store getPlayerStore(int playerNum) {
		return playersContainers.get(playerNum - 1).get(housesPerPlayer + 1);
	}

	public List<Store> getPlayerContainers(int playerNum) {
		return playersContainers.get(playerNum - 1);
	}

}
