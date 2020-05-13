package kalah.ui.format;

import kalah.game.model.Board;
import kalah.ui.format.theme.ConsoleGridTheme;

public class ConsoleGridFormat implements ConsoleFormat {

	private ConsoleGridTheme theme;

	public ConsoleGridFormat(ConsoleGridTheme theme) {
		this.theme = theme;
	}
	
	@Override
	public String getBoardString(Board board) {
		return null;
	}

	@Override
	public String getPlayerInputPrompt(int playerNum, String cancelToken) {
        return String.format("Player P%d's turn - Specify house number or '%s' to quit: ", playerNum, cancelToken);
	}

	@Override
	public String getEmptyHouseMsg() {
		return "House is empty. Move again.";
	}

	@Override
	public String getScoreMsg(int playerNum, int score) {
        return String.format("\tplayer %d:%d", playerNum, score);
	}

	@Override
	public String getWinMsg(int playerNum) {
        return String.format("Player %d wins!", playerNum);
	}

	@Override
	public String getTieMsg() {
		return "A Tie!";
	}

	@Override
	public String getGameOverMsg() {
		return "Game over";
	}
}
