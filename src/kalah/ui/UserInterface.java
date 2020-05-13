package kalah.ui;

import kalah.game.model.Board;

public interface UserInterface {

	/**
	 * Return true if the board can be fully interacted with by this user interface implementation. 
	 */
	public boolean boardCompatibilityCheck(Board board);

	public void renderBoard(Board board);

	public int getInput(Board board, int playerNum);

	public void onEmptyHouse();

	public void onQuit(Board board);

	public void onGameOver(Board board);

}
