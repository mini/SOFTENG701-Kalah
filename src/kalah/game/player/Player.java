package kalah.game.player;

import kalah.game.model.Board;

public abstract class Player {

	/**
	 * Return true if the board can be fully interacted with by this user interface
	 * implementation.
	 */
	public boolean boardCompatibilityCheck(Board board) {
		return true;
	};

	public abstract PlayerType getPlayerType();

	public void renderBoard(Board board) {
	};

	public abstract int getInput(Board board, int playerNum);

	public void onEmptyHouse() {
	};

	public void onQuit(Board board) {
	};

	public void onGameOver(Board board) {
	};

	public enum PlayerType {
		LOCAL, REMOTE, CPU;
	}

}
