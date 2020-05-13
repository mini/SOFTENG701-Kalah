package kalah.game;

import kalah.exception.EmptyHouseKalahException;
import kalah.game.model.Board;
import kalah.game.rule.RuleSet;
import kalah.ui.UserInterface;

public class GameEngine {

	private RuleSet ruleset;
	private UserInterface ui;

	public GameEngine(RuleSet ruleset, UserInterface ui) {
		this.ruleset = ruleset;
		this.ui = ui;
	}

	public void runGameLoop(Board board) {
		int currentPlayer = ruleset.getFirstPlayer();
		boolean endedNaturally = true;
		while (!ruleset.checkGameOver(board, currentPlayer)) {
			try {
				ui.renderBoard(board);
				int response = ui.getInput(board, currentPlayer);
				if (response == 0) {
					endedNaturally = false;
					break;
				}
				currentPlayer = ruleset.processTurn(board, currentPlayer, response);
			} catch (EmptyHouseKalahException e) {
				ui.onEmptyHouse();
			}
		}

		if (endedNaturally) {
			ui.onGameOver(board);
		} else {
			ui.onQuit(board);
		}
	}
}
