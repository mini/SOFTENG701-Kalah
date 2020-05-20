package kalah.game.player;

import com.qualitascorpus.testsupport.IO;

import kalah.game.GameEngine;
import kalah.game.model.Board;
import kalah.ui.ConsoleRenderer;

public class QualitasIOPlayer implements Player {
	private static final String QUIT_GAME_TOKEN = "q";
	private static final int REQUIRED_PLAYERS = 2;

	private IO io;
	private ConsoleRenderer cr;

	public QualitasIOPlayer(IO io, ConsoleRenderer cr) {
		this.io = io;
		this.cr = cr;
	}

	@Override
	public boolean boardCompatibilityCheck(Board board) {
		return board.getNumPlayers() == REQUIRED_PLAYERS;
	}
	
	@Override
	public PlayerType getPlayerType() {
		return PlayerType.LOCAL;
	}

	@Override
	public void renderBoard(Board board) {
		for (String line : cr.getBoardRepresentation(board)) {
			io.println(line);
		}
	}

	@Override
	public int getInput(Board board, int playerNum) {
		return io.readInteger(cr.getPlayerInputPrompt(playerNum, QUIT_GAME_TOKEN), 1, board.getHousesPerPlayer(), GameEngine.QUIT_VALUE, QUIT_GAME_TOKEN);
	}

	@Override
	public void onEmptyHouse() {
		io.println(cr.getEmptyHouseMsg());
	}

	@Override
	public void onQuit(Board board) {
		io.println(cr.getGameOverMsg());
		renderBoard(board);
	}

	@Override
	public void onGameOver(Board board) {
		renderBoard(board);
		onQuit(board);
		for (int i = 1; i <= board.getNumPlayers(); i++) {
			io.println(cr.getScoreMsg(i, board.getPlayerScore(i)));
		}

		int winner = board.getCurrentFirstPlace();
		if (winner == Board.NO_WINNER) {
			io.println(cr.getTieMsg());
		} else {
			io.println(cr.getWinMsg(winner));
		}
	}
}
