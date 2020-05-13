package kalah.game.rule;

import kalah.exception.EmptyHouseKalahException;
import kalah.game.model.Board;
import kalah.game.model.House;
import kalah.game.model.Pit;
import kalah.game.model.Store;

public class StandardKalahRuleSet implements RuleSet {

	private static final int PLAYER1 = 1;
	private static final int PLAYER2 = 2;

	@Override
	public int getFirstPlayer() {
		return PLAYER1;
	}

	@Override
	public int processTurn(Board board, int playerNum, int selectedHouse) {
		House house = board.getPlayerHouse(playerNum, selectedHouse);
		if (house.isEmpty()) {
			throw new EmptyHouseKalahException("House is empty");
		}

		int seedsToSow = house.takeAllSeeds();
		Pit pit = house;
		while (seedsToSow > 0) {
			pit = board.getNextPit(pit);
			if (pit instanceof Store && pit.getOwner() != playerNum) {
				continue;
			}
			pit.sowSeed();
			seedsToSow--;
		}

		if (pit.getOwner() == playerNum) {
			if (pit instanceof Store) {
				return playerNum;
			} else if (pit.getNumSeeds() == 1) {
				House oppositeHouse = getOppositeHouse(board, (House) pit);
				if (!oppositeHouse.isEmpty()) {
					Pit playerStore = board.getPlayerStore(playerNum);
					playerStore.sowSeeds(oppositeHouse.takeAllSeeds());
					playerStore.sowSeeds(((House) pit).takeAllSeeds());
				}

			}
		}

		return playerNum == PLAYER1 ? PLAYER2 : PLAYER1;
	}

	private House getOppositeHouse(Board board, House currentHouse) {
		int otherIndex = board.getHousesPerPlayer() - currentHouse.getIndex() + 1;
		int otherPlayer = currentHouse.getOwner() == PLAYER1 ? PLAYER2 : PLAYER1;
		return board.getPlayerHouse(otherPlayer, otherIndex);
	}

	@Override
	public boolean checkGameOver(Board board, int currentPlayerNum) {
		for (Pit pit : board.getPlayerPits(currentPlayerNum)) {
			if (pit instanceof House && !pit.isEmpty()) {
				return false;
			}
		}
		return true;
	}

}
