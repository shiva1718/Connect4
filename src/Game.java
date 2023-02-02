public class Game implements Runnable {
    private Board board;
    private Player player1;
    private Player player2;


    public Game() {
        board = new Board();
        player1 = new Bot(CellState.RED, 4);
        player2 = new HumanPlayer("Human player", CellState.YELLOW);
//
//        player1 = new HumanPlayer("Player 1", CellState.RED);
//        player2 = new Bot(CellState.YELLOW, 4);
//        player2 = new HumanPlayer("Player 2", CellState.YELLOW);
    }

    public void run() {
        Player currentPlayer = player1;
        boolean isRunning = true;
        while (isRunning) {
            System.out.println(board);
            boolean validMove = false;
            int col = 0;
            while (!validMove) {
                col = currentPlayer.makeMove(board);
                validMove = board.insertAtColumn(col, currentPlayer.getColor());
                if (!validMove) {
                    System.out.println("Invalid move. Please try again.");
                }
            }
            if (board.isWon(col)) {
                System.out.println(currentPlayer + " won!");
                isRunning = false;
            } else if (board.isDraw()) {
                System.out.println("Draw!");
                isRunning = false;
            } else {
                currentPlayer = switchPlayer(currentPlayer);
            }
        }
        System.out.println(board);
    }

//    public boolean isDraw() {
//        return countFilled == Board.ROWS * Board.COLUMNS;
//    }

//    private boolean isWon(int column) {
//        int row = 0;
//        for (int i = Board.ROWS - 1; i >= 0; i--) {
//            if (board.getCellState(i, column) != CellState.EMPTY) {
//                row = i;
//                break;
//            }
//        }
//        CellState currentPlayer = board.getCellState(row, column);
//        int consecutive = 1;
//        for (int i = column - 1; i >= 0; i--) {
//            if (board.getCellState(row, i) != currentPlayer) {
//                break;
//            }
//            consecutive++;
//        }
//        for (int i = column + 1; i < Board.COLUMNS; i++) {
//            if (board.getCellState(row, i) != currentPlayer) {
//                break;
//            }
//            consecutive++;
//        }
//        if (consecutive >= 4) {
//            return true;
//        }
//        consecutive = 1;
//        for (int i = row - 1; i >= 0; i--) {
//            if (board.getCellState(i, column) != currentPlayer) {
//                break;
//            }
//            consecutive++;
//        }
//        if (consecutive >= 4) {
//            return true;
//        }
//
//        // left-up to right-down
//        consecutive = 1;
//        for (int i = row - 1, j = column + 1; i >= 0 && j < Board.COLUMNS; i--, j++) {
//            if (board.getCellState(i, j) != currentPlayer) {
//                break;
//            }
//            consecutive++;
//        }
//        for (int i = row + 1, j = column - 1; i < Board.ROWS && j >= 0; i++, j--) {
//            if (board.getCellState(i, j) != currentPlayer) {
//                break;
//            }
//            consecutive++;
//        }
//        if (consecutive >= 4) {
//            return true;
//        }
//
//        // left-down to right-up
//        consecutive = 1;
//        for (int i = row + 1, j = column + 1; i < Board.ROWS && j < Board.COLUMNS; i++, j++) {
//            if (board.getCellState(i, j) != currentPlayer) {
//                break;
//            }
//            consecutive++;
//        }
//        for (int i = row - 1, j = column - 1; i >= 0 && j >= 0; i--, j--) {
//            if (board.getCellState(i, j) != currentPlayer) {
//                break;
//            }
//            consecutive++;
//        }
//        if (consecutive >= 4) {
//            return true;
//        }
//        return false;
//
//    }

    private Player switchPlayer(Player p) {
        if (p == player1) {
            return player2;
        } else {
            return player1;
        }
    }

}
