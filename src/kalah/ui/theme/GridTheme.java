package kalah.ui.theme;

public interface GridTheme {
	
	public String getSpacer();
	
	public String getVBorder();

	public String getHBorder();

	public String getCorner();

	public String getHouseCellContent(int houseNum, int numSeeds);

	public String getStoreCellPlayerLabel(int playerNum);

	public String getSeedCount(int numSeeds);
}