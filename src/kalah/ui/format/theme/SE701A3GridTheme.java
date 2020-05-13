package kalah.ui.format.theme;

import kalah.util.StringUtils;

public class SE701A3GridTheme implements GridTheme {
	@Override
	public int getStoreCellWidth() {
		return 4;
	}

	@Override
	public int getHouseCellWidth() {
		return 7;
	}

	@Override
	public String getSpacer() {
		return " ";
	}

	@Override
	public String getVBorder() {
		return "|";
	}

	@Override
	public String getHBorder() {
		return "-";
	}

	@Override
	public String getCorner() {
		return "+";
	}

	@Override
	public String getHouseCellContent(int houseNum, int numSeeds) {
		return houseNum + "[" + getSeedCount(numSeeds) + "]";
	}

	@Override
	public String getPlayerLabel(int playerNum) {
		return "P" + playerNum;
	}

	@Override
	public String getSeedCount(int numSeeds) {
		return StringUtils.padNum(numSeeds, 2);
	}
}
