package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {
		int i = io.readInteger("A", 1, 4, -1, "q");
		System.out.println(i);
		
		// Generate board
		//TODO
		// Pass board to game engine to run
		//TODO
	}
}
