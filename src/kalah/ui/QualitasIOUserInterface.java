package kalah.ui;

import com.qualitascorpus.testsupport.IO;

import kalah.game.GameEngine;
import kalah.game.model.Board;
import kalah.ui.format.ConsoleRenderer;

public class QualitasIOUserInterface implements UserInterface {
	private static final String QUIT_GAME_TOKEN = "q";
	private static final int REQUIRED_PLAYERS = 2;

	private IO io;
	private ConsoleRenderer cf;

	public QualitasIOUserInterface(IO io, ConsoleRenderer cf) {
		this.io = io;
		this.cf = cf;
	}

	@Override
	public boolean boardCompatibilityCheck(Board board) {
		return board.getNumPlayers() == REQUIRED_PLAYERS;
	}

	@Override
	public void renderBoard(Board board) {
		for (String line : cf.getBoardRepresentation(board)) {
			io.println(line);
		}
	}

	@Override
	public int getInput(Board board, int playerNum) {
		return io.readInteger(cf.getPlayerInputPrompt(playerNum, QUIT_GAME_TOKEN), 1, board.getHousesPerPlayer(), GameEngine.QUIT_VALUE, QUIT_GAME_TOKEN);
	}

	@Override
	public void onEmptyHouse() {
		io.println(cf.getEmptyHouseMsg());
	}

	@Override
	public void onQuit(Board board) {
		io.println(cf.getGameOverMsg());
		renderBoard(board);
	}

	@Override
	public void onGameOver(Board board) {
		renderBoard(board);
		onQuit(board);
		for (int i = 1; i <= board.getNumPlayers(); i++) {
			io.println(cf.getScoreMsg(i, board.getPlayerScore(i)));
		}

		int winner = board.getCurrentFirstPlace();
		if (winner == Board.NO_WINNER) {
			io.println(cf.getTieMsg());
		} else {
			io.println(cf.getWinMsg(winner));
		}
	}
}
