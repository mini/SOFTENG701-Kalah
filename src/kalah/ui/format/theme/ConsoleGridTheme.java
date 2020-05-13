package kalah.ui.format.theme;

public interface ConsoleGridTheme {

	public String getVBorder();

	public String getHBorder();

	public String getCorner();

	public String getHouseCell(int houseNum, int numSeeds);

	public String getPlayer(int playerNum);

	public String getSeedCount(int numSeeds);
}
