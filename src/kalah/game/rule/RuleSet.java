package kalah.game.rule;

import kalah.game.model.Board;

public interface RuleSet {
	
	public boolean boardCompatibilityCheck(Board board);

	public int getFirstPlayer();

	public int processTurn(Board board, int playerNum, int selectedHouse);

	public boolean checkGameOver(Board board, int currentPlayerNum);
}
