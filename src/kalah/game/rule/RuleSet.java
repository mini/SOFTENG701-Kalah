package kalah.game.rule;

import kalah.game.model.Board;

public interface RuleSet {
	
	/**
	 * Return true if the board is compatible with this implementation of Rules 
	 */
	public boolean boardCompatibilityCheck(Board board);

	/**
	 * Decide who should go first
	 */
	public int getFirstPlayer();


	/**
	 * Process a turn and return who should play next 
	 */
	public int processTurn(Board board, int playerNum, int selectedHouse);

	/**
	 * Called before a player has their turn. 
	 */
	public boolean checkGameOver(Board board, int currentPlayerNum);
}
