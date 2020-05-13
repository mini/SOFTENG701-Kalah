package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

import kalah.game.model.Board;
import kalah.ui.QualitasIOUserInterface;
import kalah.ui.format.ConsoleGridFormat;
import kalah.ui.format.theme.SE701A3ConsoleGridTheme;

/**
 * This class is the starting point for a Kalah implementation using the test
 * infrastructure.
 */
public class Kalah {
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}

	public void play(IO io) {
		Board b = new Board(2, 6, 4);
		QualitasIOUserInterface ui = new QualitasIOUserInterface(io, new ConsoleGridFormat(new SE701A3ConsoleGridTheme()));
//		ui.renderBoard(b);
		ui.onQuit(b);
		// Generate board
		// TODO
		// Pass board to game engine to run
		// TODO
	}
}
