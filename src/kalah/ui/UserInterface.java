package kalah.ui;

import kalah.game.model.Board;

public interface UserInterface {

	public boolean boardCompatibilityCheck(Board board);

	public void renderBoard(Board board);

	public int getInput(Board board, int playerNum);

	public void onEmptyHouse();

	public void onQuit(Board board);

	public void onGameOver(Board board);

}
