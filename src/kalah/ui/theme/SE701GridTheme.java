package kalah.ui.theme;

import kalah.util.StringUtils;

public class SE701GridTheme implements GridTheme {
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
	public String getStoreCellPlayerLabel(int playerNum) {
		return "P" + playerNum;
	}

	@Override
	public String getSeedCount(int numSeeds) {
		return StringUtils.padNum(numSeeds, 2);
	}
}
