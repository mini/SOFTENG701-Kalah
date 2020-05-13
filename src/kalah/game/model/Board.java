package kalah.game.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
	public static final int NO_WINNER = 0;

	private int numPlayers;
	private int housesPerPlayer;
	private int pitsPerPlayer;
	private List<List<Pit>> playersPits = new ArrayList<>();

	public Board(int numPlayers, int housesPerPlayer, int initialSeeds) {
		this.numPlayers = numPlayers;
		this.housesPerPlayer = housesPerPlayer;
		this.pitsPerPlayer = housesPerPlayer + 1;

		for (int i = 1; i <= numPlayers; i++) {
			List<Pit> playerPits = new ArrayList<>();
			for (int j = 1; j <= housesPerPlayer; j++) {
				playerPits.add(new House(i, initialSeeds, j));
			}
			playerPits.add(new Store(i, pitsPerPlayer));
			playersPits.add(playerPits);
		}
	}

	public int getPlayerScore(int playerNum) {
		int sum = 0;
		for (Pit pit : playersPits.get(playerNum - 1)) {
			sum += pit.getNumSeeds();
		}
		return sum;
	}

	public int getCurrentFirstPlace() {
		int bestScore = 0;
		int bestPlayer = 0;
		boolean tieExists = false;
		for (int i = 1; i <= numPlayers; i++) {
			int score = getPlayerScore(i);
			if (score > bestScore) {
				bestScore = score;
				bestPlayer = i;
				tieExists = false;
			} else if (score == bestScore) {
				tieExists = true;
			}
		}

		if (tieExists) {
			return NO_WINNER;
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
		return (Store) playersPits.get(playerNum - 1).get(housesPerPlayer);
	}

	public House getPlayerHouse(int playerNum, int houseIndex) {
		if (houseIndex > housesPerPlayer) {
			return null;
		}
		return (House) playersPits.get(playerNum - 1).get(houseIndex - 1);
	}

	public List<Pit> getPlayerPits(int playerNum) {
		List<Pit> output = new ArrayList<>();
		for (Pit pit : playersPits.get(playerNum - 1)) {
			output.add(pit);
		}
		return output;
	}

	public Pit getNextPit(Pit current) {
		int nextOwner = current.getOwner();
		int nextIndex = current.getIndex();
		if (nextIndex == pitsPerPlayer) {
			nextOwner++;
			nextOwner = nextOwner > numPlayers ? 1 : nextOwner;
			nextIndex = 1;
		} else {
			nextIndex++;
			nextIndex = nextIndex > pitsPerPlayer ? 1 : nextIndex;
		}

		return playersPits.get(nextOwner - 1).get(nextIndex - 1);
	}
}
