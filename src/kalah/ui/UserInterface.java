package kalah.ui;

import kalah.game.model.Board;
import kalah.util.KalahGameException;

public interface UserInterface {

	public void renderBoard(Board board);

	public int getInput(Board board, int playerNum);

	 public void onGameError(KalahGameException e);

	public void onQuit(Board board);

	public void onGameOver(Board board);
}
