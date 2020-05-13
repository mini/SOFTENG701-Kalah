package kalah.game.model;

public class Pit {
	protected int numSeeds = 0;
	protected int ownerNum;
	protected int index;

	public Pit(int ownerNum, int index) {
		this.ownerNum = ownerNum;
		this.index = index;
	}
	
	public void sowSeed() {
		numSeeds++;
	}
	
	public void sowSeeds(int amount) {
		numSeeds += amount;
	}

	public int getNumSeeds() {
		return numSeeds;
	}
	
	public int getOwner() {
		return ownerNum;
	}
	
	public int getIndex() {
		return index;
	}
	
	public boolean isEmpty() {
		return numSeeds == 0;
	}

	@Override
	public String toString() {
		return String.format("Store [seeds=%s, owner=%s, index=%s]", numSeeds, ownerNum, index);
	}
}
