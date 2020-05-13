package kalah.ui.format.theme;

import kalah.util.StringUtils;

public class SE701A3ConsoleGridTheme implements ConsoleGridTheme {

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
	public String getHouseCell(int houseNum, int numSeeds) {
		return houseNum + "[" + getSeedCount(numSeeds) + "]";
	}

	@Override
	public String getPlayer(int playerNum) {
		return "P" + playerNum;
	}

	@Override
	public String getSeedCount(int numSeeds) {
		return StringUtils.padNum(numSeeds, 2);
	}
}
