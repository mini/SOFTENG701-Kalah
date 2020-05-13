package kalah.ui;

import com.qualitascorpus.testsupport.IO;

import kalah.game.model.Board;
import kalah.ui.format.ConsoleFormat;
import kalah.util.KalahGameException;

public class QualitasIOUserInterface implements UserInterface {

	private static final String QUIT_GAME_TOKEN = "q";
	private IO io;
	private ConsoleFormat cf;

	public QualitasIOUserInterface(IO io, ConsoleFormat cf) {
		this.io = io;
		this.cf = cf;
	}

	@Override
	public void renderBoard(Board board) {
		String[] lines = cf.getBoardString(board).split("\n");
		for(String line: lines) {
			io.println(line);
		}
	}

	@Override
	public int getInput(Board board, int playerNum) {
		return io.readInteger(cf.getPlayerInputPrompt(playerNum, QUIT_GAME_TOKEN), 1, board.getHousesPerPlayer(), -1, QUIT_GAME_TOKEN);
	}

	@Override
	public void onGameError(KalahGameException e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onQuit(Board board) {
		io.println(cf.getGameOverMsg());
		renderBoard(board);
	}

	@Override
	public void onGameOver(Board board) {
		onQuit(board);
		for (int i = 1; i <= board.getNumPlayers(); i++) {
			io.println(cf.getScoreMsg(i, board.getPlayerScore(i)));
		}

		int winner = board.getWinner();
		if (winner == 0) {
			io.println(cf.getTieMsg());
		} else {
			io.println(cf.getWinMsg(winner));
		}
	}
}
