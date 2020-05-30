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

	/**
	 * Gets a player's score
	 * @param playerNum (1 indexed)
	 * @return the player's score
	 */
	public int getPlayerScore(int playerNum) {
		int sum = 0;
		for (Pit pit : playersPits.get(playerNum - 1)) {
			sum += pit.getNumSeeds();
		}
		return sum;
	}

	/**
	 * Finds the highest scoring player. If there a tie then {@link Board#NO_WINNER} is returned
	 * @return the best play's id
	 */
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
	
	public int getPitsPerPlayer() {
		return pitsPerPlayer;
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

	/**
	 * Gets the next pit. The ordering goes from H1P1, ..., HnP1, H1P2, ..., HnP2, 
	 */
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
	
	public Pit getNextPitForOwner(Pit current, int distance) {
		Pit pit = current;
		for(int i = 0; i < distance; i++) {
			pit = getNextPit(pit);
		}
		return pit;
	}
}
