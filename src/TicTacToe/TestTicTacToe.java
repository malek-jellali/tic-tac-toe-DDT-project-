package TicTacToe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

class TestTicTacToe {
	@SuppressWarnings("deprecation")
	@Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testPlayWithinBounds() {
        TicTacToe game = new TicTacToe();
        game.play(0, 0); // Should not throw any exception
        assertEquals('X', game.getMarkAt(0, 0));
    }

    @Test
    public void testPlayOutOfBoundsX() {
        TicTacToe game = new TicTacToe();
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Invalid coordinates");
        game.play(2, 0); // X coordinate out of bounds (0, 1, 2) should throw RuntimeException
    }

    @Test
    public void testPlayOutOfBoundsY() {
        TicTacToe game = new TicTacToe();
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Invalid coordinates");
        game.play(0, 2); // Y coordinate out of bounds (0, 1, 2) should throw RuntimeException
    }
    
    @Test
    public void testCurrentPlayerAtStart() {
        TicTacToe game = new TicTacToe();
        assertEquals('X', game.getCurrentPlayer());
    }
    @Test
    public void testPlayerAlternation() {
        TicTacToe game = new TicTacToe();

        char[] players = {'X', 'O'};
        int numberOfMoves = 4;  // Changer cela selon le nombre de mouvements à tester

        for (int i = 0; i < numberOfMoves; i++) {
            assertEquals(players[i % 2], game.getCurrentPlayer());
            game.play(i / 3, i % 3);
        }
    }

    @Test
    public void testHorizontalWin() {
        TicTacToe game = new TicTacToe();
        game.play(0, 0); // X
        game.play(1, 0); // O
        game.play(0, 1); // X
        game.play(1, 1); // O
        game.play(0, 2); // X
        assertEquals("Le joueur X a gagné !", game.getWinner());
    }

    @Test
    public void testVerticalWin() {
        TicTacToe game = new TicTacToe();
        game.play(0, 0); // X
        game.play(0, 1); // O
        game.play(1, 0); // X
        game.play(1, 1); // O
        game.play(2, 0); // X
        assertEquals("Le joueur X a gagné !", game.getWinner());
    }

    @Test
    public void testDiagonalWin1() {
        TicTacToe game = new TicTacToe();
        game.play(0, 0); // X
        game.play(0, 1); // O
        game.play(1, 1); // X
        game.play(0, 2); // O
        game.play(2, 2); // X
        assertEquals("Le joueur X a gagné !", game.getWinner());
    }

    @Test
    public void testDiagonalWin2() {
        TicTacToe game = new TicTacToe();
        game.play(0, 2); // X
        game.play(0, 0); // O
        game.play(1, 1); // X
        game.play(0, 1); // O
        game.play(2, 0); // X
        assertEquals("Le joueur X a gagné !", game.getWinner());
    }

    @Test
    public void testGameInProgress() {
        TicTacToe game = new TicTacToe();
        game.play(0, 0); // X
        game.play(1, 0); // O
        game.play(0, 1); // X
        assertEquals("Le jeu est en cours.", game.getWinner());
    }
    @Test
    public void testGameDraw() {
        TicTacToe game = new TicTacToe();

        // Play moves without a winner
        game.play(0, 0); // X
        game.play(0, 1); // O
        game.play(0, 2); // X
        game.play(1, 1); // O
        game.play(1, 0); // X
        game.play(2, 1); // O
        game.play(1, 2); // X
        game.play(2, 0); // O
        game.play(2, 2); // X

        assertTrue(game.isGameDraw());
    }



    

}