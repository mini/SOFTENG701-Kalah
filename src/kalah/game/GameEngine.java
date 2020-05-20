package kalah.game;

import kalah.exception.EmptyHouseKalahException;
import kalah.exception.IncompatibleBoardKalahException;
import kalah.game.model.Board;
import kalah.game.player.Player;
import kalah.game.player.Player.PlayerType;
import kalah.game.rule.RuleSet;

public class GameEngine {
	public static final int QUIT_VALUE = 0;

	private RuleSet ruleset;
	private Player[] players;

	public GameEngine(RuleSet ruleset, Player... players) {
		this.ruleset = ruleset;
		this.players = players;
	}

	public void runGameLoop(Board board) {
		if (!ruleset.boardCompatibilityCheck(board)) {
			throw new IncompatibleBoardKalahException("RuleSet is not compatible with current board");
		}
		for (Player p : players) {
			if (!p.boardCompatibilityCheck(board)) {
				throw new IncompatibleBoardKalahException("UI is not compatible with current board");
			}
		}

		int player = ruleset.getFirstPlayer();
		boolean endedNaturally = true;
		while (!ruleset.checkGameOver(board, player)) {
			try {
				players[player - 1].renderBoard(board);
				int response = players[player - 1].getInput(board, player);
				if (response == QUIT_VALUE) {
					endedNaturally = false;
					break;
				}
				player = ruleset.processTurn(board, player, response);
			} catch (EmptyHouseKalahException e) {
				players[player - 1].onEmptyHouse();
			}
		}

		// Send event to all remote players but only first local
		boolean seenLocalPlayer = false;
		for (Player p : players) {
			if (p.getPlayerType() == PlayerType.LOCAL) {
				if (seenLocalPlayer) {
					continue;
				}
				seenLocalPlayer = true;
			}

			if (endedNaturally) {
				p.onGameOver(board);
			} else {
				p.onQuit(board);
			}
		}
	}
}
