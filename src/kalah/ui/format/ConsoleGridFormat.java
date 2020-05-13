package kalah.ui.format;

import java.util.List;

import kalah.game.model.Board;
import kalah.game.model.Store;
import kalah.ui.format.theme.ConsoleGridTheme;
import kalah.util.StringUtils;

public class ConsoleGridFormat implements ConsoleFormat {
	private static final int PLAYER1_NUM = 1;
	private static final int PLAYER2_NUM = 2;
	private static final String SPACER = " ";
	private static final int STORE_WIDTH = 4;
	private static final int HOUSE_WIDTH = 7;
	private ConsoleGridTheme theme;

	public ConsoleGridFormat(ConsoleGridTheme theme) {
		this.theme = theme;
	}

	@Override
	public String[] getBoardRepresentation(Board board) {
		int numHousesAcross = board.getHousesPerPlayer();
		List<Store> player1Containers = board.getPlayerContainers(PLAYER1_NUM);
		List<Store> player2Containers = board.getPlayerContainers(PLAYER2_NUM);

		return new String[] {
				horizontalBorder(numHousesAcross),
				upperLine(numHousesAcross, player2Containers, player1Containers.get(numHousesAcross)),
				midHorizontalBorder(numHousesAcross),
				lowerLine(numHousesAcross, player1Containers, player2Containers.get(numHousesAcross)),
				horizontalBorder(numHousesAcross),
		};
	}

	private String upperLine(int numHousesAcross, List<Store> player2Houses, Store player1Store) {
		StringBuilder sb = new StringBuilder();
		sb.append(theme.getVBorder());
		sb.append(StringUtils.center(theme.getPlayerLabel(PLAYER2_NUM), STORE_WIDTH, SPACER));
		for (int i = 0; i < numHousesAcross; i++) {
			sb.append(theme.getVBorder());
			sb.append(StringUtils.center(theme.getHouseCell(numHousesAcross - i, player2Houses.get(numHousesAcross - i - 1).getNumSeeds()), HOUSE_WIDTH, SPACER));
		}
		sb.append(theme.getVBorder());
		sb.append(StringUtils.center(theme.getSeedCount(player1Store.getNumSeeds()), STORE_WIDTH, SPACER));
		sb.append(theme.getVBorder());
		return sb.toString();
	}

	private String lowerLine(int numHousesAcross, List<Store> player1Houses, Store player2Store) {
		StringBuilder sb = new StringBuilder();
		sb.append(theme.getVBorder());
		sb.append(StringUtils.center(theme.getSeedCount(player2Store.getNumSeeds()), STORE_WIDTH, SPACER));
		for (int i = 0; i < numHousesAcross; i++) {
			sb.append(theme.getVBorder());
			sb.append(StringUtils.center(theme.getHouseCell(i + 1, player1Houses.get(i).getNumSeeds()), HOUSE_WIDTH, SPACER));
		}
		sb.append(theme.getVBorder());
		sb.append(StringUtils.center(theme.getPlayerLabel(PLAYER1_NUM), STORE_WIDTH, SPACER));
		sb.append(theme.getVBorder());
		return sb.toString();
	}

	private String horizontalBorder(int numHouses) {
		StringBuilder sb = new StringBuilder();
		sb.append(theme.getCorner());
		sb.append(StringUtils.repeat(theme.getHBorder(), STORE_WIDTH));

		for (int i = 0; i < numHouses; i++) {
			sb.append(theme.getCorner());
			sb.append(StringUtils.repeat(theme.getHBorder(), HOUSE_WIDTH));
		}
		sb.append(theme.getCorner());
		sb.append(StringUtils.repeat(theme.getHBorder(), STORE_WIDTH));
		sb.append(theme.getCorner());
		return sb.toString();
	}

	private String midHorizontalBorder(int numHouses) {
		StringBuilder sb = new StringBuilder();
		sb.append(theme.getVBorder());
		sb.append(StringUtils.repeat(SPACER, STORE_WIDTH));
		sb.append(theme.getVBorder());
		for (int i = 0; i < numHouses; i++) {
			if (i != 0)
				sb.append(theme.getCorner());
			sb.append(StringUtils.repeat(theme.getHBorder(), HOUSE_WIDTH));
		}
		sb.append(theme.getVBorder());
		sb.append(StringUtils.repeat(SPACER, STORE_WIDTH));
		sb.append(theme.getVBorder());
		return sb.toString();
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
