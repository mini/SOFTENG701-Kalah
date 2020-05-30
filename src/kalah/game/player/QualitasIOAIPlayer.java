package kalah.game.player;

import com.qualitascorpus.testsupport.IO;

import kalah.game.model.Board;
import kalah.game.model.House;
import kalah.game.model.Pit;
import kalah.game.model.Store;
import kalah.ui.ConsoleRenderer;

public class QualitasIOAIPlayer extends Player {

	private static final int PLAYER1 = 1;
	private static final int PLAYER2 = 2;
	private static final int REQUIRED_PLAYERS = 2;

	private IO io;
	private ConsoleRenderer cr;

	public QualitasIOAIPlayer() {
	}

	public QualitasIOAIPlayer(IO io, ConsoleRenderer cr) {
		this.io = io;
		this.cr = cr;
	}

	@Override
	public boolean boardCompatibilityCheck(Board board) {
		return board.getNumPlayers() == REQUIRED_PLAYERS;
	}

	@Override
	public PlayerType getPlayerType() {
		return PlayerType.CPU;
	}

	@Override
	public void onEmptyHouse() {
		System.err.println("AI Faulted");
	}

	@Override
	public int getInput(Board board, int playerNum) {
		if (io != null && cr != null) {
			for (String line : cr.getBoardRepresentation(board)) {
				io.println(line);
			}
		}

		int choosenHouse = 0;
		String reason;

		if ((choosenHouse = checkAdditionalMove(board, playerNum)) != 0) {
			reason = "leads to an extra move";
		} else if ((choosenHouse = checkCapture(board, playerNum)) != 0) {
			reason = "leads to a capture";
		} else {
			choosenHouse = getFirstLegalMove(board, playerNum);
			reason = "is the first legal move";
		}

		if (io != null && cr != null) {
			io.println(String.format("Player P%d (Robot) chooses house #%d because it %s", playerNum, choosenHouse, reason));
		}

		return choosenHouse;
	}

	private int checkAdditionalMove(Board board, int playerNum) {
		for (int i = 1; i <= board.getHousesPerPlayer(); i++) {
			House house = board.getPlayerHouse(playerNum, i);
			Pit endPit = board.getNextPitForOwner(house, house.getNumSeeds());
			if (!house.isEmpty() && endPit instanceof Store && endPit.getOwner() == playerNum) {
				return i;
			}
		}
		return 0;
	}

	private int checkCapture(Board board, int playerNum) {
		for (int i = 1; i <= board.getHousesPerPlayer(); i++) {
			House house = board.getPlayerHouse(playerNum, i);

			Pit endHouse = board.getNextPitForOwner(house, house.getNumSeeds());
			if (i == 6) {
				System.out.println("00---000-0--0" + house);
				System.out.println("00---000-0--0" + endHouse);
			}
			if (!house.isEmpty() && endHouse instanceof House && endHouse.getOwner() == playerNum && endHouse.isEmpty()) {
				House opposite = getOppositeHouse(board, (House) endHouse);
				if (!opposite.isEmpty()) {
					return i;
				}
			}
		}
		return 0;
	}

	private House getOppositeHouse(Board board, House currentHouse) {
		int otherIndex = board.getHousesPerPlayer() - currentHouse.getIndex() + 1;
		int otherPlayer = currentHouse.getOwner() == PLAYER1 ? PLAYER2 : PLAYER1;
		return board.getPlayerHouse(otherPlayer, otherIndex);
	}

	private int getFirstLegalMove(Board board, int playerNum) {
		for (int i = 1; i <= board.getHousesPerPlayer(); i++) {
			if (!board.getPlayerHouse(playerNum, i).isEmpty()) {
				return i;
			}
		}
		return 0; // Will never happen, due to gameover check
	}
}
