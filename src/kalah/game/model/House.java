package kalah.game.model;

public class House extends Store {

	public House(int startingSeeds) {
		numSeeds = startingSeeds;
	}
	
	public int takeAllSeeds() {
		int seeds = numSeeds;
		numSeeds = 0;
		return seeds;
	}
}
