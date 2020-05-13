package kalah.ui.format;

import kalah.game.model.Board;
import kalah.ui.format.theme.GridTheme;
import kalah.util.StringUtils;

public class SE701A3ConsoleRenderer implements ConsoleRenderer {
	private static final int PLAYER1 = 1;
	private static final int PLAYER2 = 2;

	private GridTheme theme;

	public SE701A3ConsoleRenderer(GridTheme theme) {
		this.theme = theme;
	}

	@Override
	public String[] getBoardRepresentation(Board board) {
		int numHousesAcross = board.getHousesPerPlayer();

		return new String[] {
				horizontalBorder(numHousesAcross),
				upperLine(numHousesAcross, board),
				midHorizontalBorder(numHousesAcross),
				lowerLine(numHousesAcross, board),
				horizontalBorder(numHousesAcross),
		};
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
		return "A tie!";
	}

	@Override
	public String getGameOverMsg() {
		return "Game over";
	}

	/*
	 * WARNING: HIC SUNT DRACONES!!!!!!!!
	 * I tried a 2D character grid renderer but certain rules had waaaaay too many exceptions.
	 * These functions would actually be readable if it had in-lined constants rather than using the theme class.
	 */
	
	private String horizontalBorder(int numHouses) {
		StringBuilder sb = new StringBuilder();
		sb.append(theme.getCorner());
		sb.append(StringUtils.repeat(theme.getHBorder(), theme.getStoreCellWidth()));

		for (int i = 0; i < numHouses; i++) {
			sb.append(theme.getCorner());
			sb.append(StringUtils.repeat(theme.getHBorder(), theme.getHouseCellWidth()));
		}
		sb.append(theme.getCorner());
		sb.append(StringUtils.repeat(theme.getHBorder(), theme.getStoreCellWidth()));
		sb.append(theme.getCorner());
		return sb.toString();
	}

	//Should really parameterise this to reduce duplication, see lowerLine()
	private String upperLine(int numHousesAcross, Board board) {
		StringBuilder sb = new StringBuilder();
		sb.append(theme.getVBorder());
		sb.append(StringUtils.center(theme.getStoreCellPlayerLabel(PLAYER2), theme.getStoreCellWidth(), theme.getSpacer()));
		for (int i = 0; i < numHousesAcross; i++) {
			sb.append(theme.getVBorder());
			sb.append(StringUtils.center(
					theme.getHouseCellContent(numHousesAcross - i, board.getPlayerHouse(PLAYER2, numHousesAcross - i).getNumSeeds()),
					theme.getHouseCellWidth(),
					theme.getSpacer()));
		}
		sb.append(theme.getVBorder());
		sb.append(StringUtils.center(theme.getSeedCount(board.getPlayerStore(PLAYER1).getNumSeeds()), theme.getStoreCellWidth(), theme.getSpacer()));
		sb.append(theme.getVBorder());
		return sb.toString();
	}

	private String midHorizontalBorder(int numHouses) {
		StringBuilder sb = new StringBuilder();
		sb.append(theme.getVBorder());
		sb.append(StringUtils.repeat(theme.getSpacer(), theme.getStoreCellWidth()));
		sb.append(theme.getVBorder());
		for (int i = 0; i < numHouses; i++) {
			if (i != 0)
				sb.append(theme.getCorner());
			sb.append(StringUtils.repeat(theme.getHBorder(), theme.getHouseCellWidth()));
		}
		sb.append(theme.getVBorder());
		sb.append(StringUtils.repeat(theme.getSpacer(), theme.getStoreCellWidth()));
		sb.append(theme.getVBorder());
		return sb.toString();
	}

	private String lowerLine(int numHousesAcross, Board board) {
		StringBuilder sb = new StringBuilder();
		sb.append(theme.getVBorder());
		sb.append(StringUtils.center(theme.getSeedCount(board.getPlayerStore(PLAYER2).getNumSeeds()), theme.getStoreCellWidth(), theme.getSpacer()));
		for (int i = 1; i <= numHousesAcross; i++) {
			sb.append(theme.getVBorder());
			sb.append(StringUtils.center(theme.getHouseCellContent(i, board.getPlayerHouse(PLAYER1, i).getNumSeeds()), theme.getHouseCellWidth(), theme.getSpacer()));
		}
		sb.append(theme.getVBorder());
		sb.append(StringUtils.center(theme.getStoreCellPlayerLabel(PLAYER1), theme.getStoreCellWidth(), theme.getSpacer()));
		sb.append(theme.getVBorder());
		return sb.toString();
	}

}
