package kalah.game.model;

public class Store {
	protected int numSeeds = 0;

	public void sowSeed() {
		numSeeds++;
	}

	public int getNumSeeds() {
		return numSeeds;
	}
}
