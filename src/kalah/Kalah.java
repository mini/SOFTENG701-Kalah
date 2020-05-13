package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

import kalah.game.GameEngine;
import kalah.game.model.Board;
import kalah.game.rule.RuleSet;
import kalah.game.rule.StandardKalahRuleSet;
import kalah.ui.QualitasIOUserInterface;
import kalah.ui.format.SE701A3ConsoleStrings;
import kalah.ui.format.theme.SE701A3GridTheme;

/**
 * This class is the starting point for a Kalah implementation using the test
 * infrastructure.
 */
public class Kalah {
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}

	public void play(IO io) {
		Board board = new Board(2, 6, 4);
		QualitasIOUserInterface ui = new QualitasIOUserInterface(io, new SE701A3ConsoleStrings(new SE701A3GridTheme()));
		RuleSet rs = new StandardKalahRuleSet();
		new GameEngine(rs, ui).runGameLoop(board);
	}
}
