package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

import kalah.game.GameEngine;
import kalah.game.model.Board;
import kalah.game.player.Player;
import kalah.game.player.QualitasIOPlayer;
import kalah.game.rule.RuleSet;
import kalah.game.rule.StandardKalahRuleSet;
import kalah.ui.SE701A4ConsoleRenderer;
import kalah.ui.theme.SE701GridTheme;

/**
 * This class is the starting point for a Kalah implementation using the test
 * infrastructure.
 */
public class Kalah {
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}

	/*
	 * Basically, instantiate a RuleSet and Interface for a GameEngine to use
	 * against a Board.
	 */
	public void play(IO io) {
		Board board = new Board(2, 6, 4);
		SE701A4ConsoleRenderer renderer = new SE701A4ConsoleRenderer(new SE701GridTheme());
		Player p1 = new QualitasIOPlayer(io, renderer);
		Player p2 = new QualitasIOPlayer(io, renderer);
		RuleSet rs = new StandardKalahRuleSet();
		new GameEngine(rs, p1, p2).runGameLoop(board);
	}
}
