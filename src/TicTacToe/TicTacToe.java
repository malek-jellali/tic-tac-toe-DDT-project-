package TicTacToe;

public class TicTacToe {
    private char[][] board;  // La grille de jeu
    private char currentPlayer;  // Le joueur actuel ("X" ou "O")

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';  // On commence avec le joueur X
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';  // Initialiser la grille avec des espaces vides
            }
        }
    }

    public void play(int x, int y) {
        validateCoordinates(x, y);
        validateEmptyCell(x, y);
        placePawn(x, y);
        switchPlayer();
    }
    
    private void validateCoordinates(int x, int y) {
        if (x < 0 || x >= 3 || y < 0 || y >= 3) {
            throw new RuntimeException("Invalid coordinates");
        }
    }

    private void validateEmptyCell(int x, int y) {
        if (board[x][y] != ' ') {
            throw new RuntimeException("The cell is already occupied");
        }
    }

    private void placePawn(int x, int y) {
        board[x][y] = currentPlayer;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
    
    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public char getMarkAt(int x, int y) {
        return board[x][y];
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;  // S'il y a au moins une case vide, la grille n'est pas pleine
                }
            }
        }
        return true;  // La grille est pleine
    }

    public String getWinner() {
        // Vérifier les lignes horizontales
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return "Le joueur " + board[i][0] + " a gagné !";
            }
        }

        // Vérifier les lignes verticales
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != ' ') {
                return "Le joueur " + board[0][j] + " a gagné !";
            }
        }

        // Vérifier la première diagonale
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            return "Le joueur " + board[0][0] + " a gagné !";
        }

        // Vérifier la deuxième diagonale
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
            return "Le joueur " + board[0][2] + " a gagné !";
        }

        // Si aucun joueur n'a gagné et qu'il reste des mouvements possibles, le jeu est en cours
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return "Le jeu est en cours.";
                }
            }
        }

        // Si aucune condition de victoire n'est remplie et qu'il n'y a plus de mouvements possibles, c'est un match nul
        return "Match nul !";
    }
    
    public boolean isGameDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // If there is an empty cell, the game is not a draw
                }
            }
        }
        return true; // If all cells are filled and there is no winner, the game is a draw
    }

}
