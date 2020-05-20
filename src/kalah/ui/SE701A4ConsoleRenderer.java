package kalah.ui;

import java.util.ArrayList;
import java.util.List;

import kalah.game.model.Board;
import kalah.ui.theme.GridTheme;
import kalah.util.StringUtils;

public class SE701A4ConsoleRenderer extends SE701A3ConsoleRenderer {

	private static final int CELL_WIDTH = 7;

	public SE701A4ConsoleRenderer(GridTheme theme) {
		super(theme);
	}

	@Override
	public String[] getBoardRepresentation(Board board) {
		List<String> lines = new ArrayList<>();

		drawOuterBorder(lines);
		drawTwoCellLine(lines,
				"",
				theme.getStoreCellPlayerLabel(PLAYER2) + theme.getSpacer() + theme.getSeedCount(board.getPlayerStore(PLAYER2).getNumSeeds()));
		drawInnerBorder(lines);

		int numHousesPerPlayer = board.getHousesPerPlayer();
		for (int i = 1; i <= numHousesPerPlayer; i++) {
			int player2House = numHousesPerPlayer - i + 1;
			drawTwoCellLine(lines,
					theme.getHouseCellContent(i, board.getPlayerHouse(PLAYER1, i).getNumSeeds()),
					theme.getHouseCellContent(player2House, board.getPlayerHouse(PLAYER2, player2House).getNumSeeds()));
		}

		drawInnerBorder(lines);
		drawTwoCellLine(lines,
				theme.getStoreCellPlayerLabel(PLAYER1) + theme.getSpacer() + theme.getSeedCount(board.getPlayerStore(PLAYER1).getNumSeeds()),
				"");
		drawOuterBorder(lines);

		return lines.toArray(new String[0]);
	}

	private void drawTwoCellLine(List<String> lines, String lCell, String rCell) {
		lines.add(theme.getVBorder() +
				StringUtils.center(lCell, CELL_WIDTH, theme.getSpacer()) +
				theme.getVBorder() +
				StringUtils.center(rCell, CELL_WIDTH, theme.getSpacer()) +
				theme.getVBorder());
	}

	private void drawInnerBorder(List<String> lines) {
		lines.add(theme.getCorner() +
				StringUtils.repeat(theme.getHBorder(), CELL_WIDTH) +
				theme.getCorner() +
				StringUtils.repeat(theme.getHBorder(), CELL_WIDTH) +
				theme.getCorner());
	}

	private void drawOuterBorder(List<String> lines) {
		lines.add(theme.getCorner() +
				StringUtils.repeat(theme.getHBorder(), 1 + CELL_WIDTH * 2) +
				theme.getCorner());
	}
}
