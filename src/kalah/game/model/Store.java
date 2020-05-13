package kalah.game.model;

public class Store extends Pit {

	public Store(int ownerNum, int index) { // Couldn't just have Store -> House as then Houses are instances of Stores
		super(ownerNum, index);
	}
}
