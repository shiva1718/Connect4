import java.util.Arrays;

public class Board {
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
    public static final int TOTAL_CELLS = ROWS * COLUMNS;
    public static final int WIN_COUNT = 3;
    private CellState[][] board;
    private int[] columnHeights;
    private int countFilled = 0;

    public Board() {
        board = new CellState[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = CellState.EMPTY;
            }
        }
        columnHeights = new int[COLUMNS];
        Arrays.fill(columnHeights, ROWS - 1);
    }


    // A temporary constructor for memoization, a more stable version will be implemented later
    public Board(Board board) {
        this.board = new CellState[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                this.board[i][j] = board.getCellState(i, j);
            }
        }
//        this.columnHeights = board.columnHeights.clone();
//        this.countFilled = board.countFilled;
    }

    public boolean insertAtColumn(int columnIndex, CellState color) {
        if (columnIndex < 0 || columnIndex >= COLUMNS || columnHeights[columnIndex] < 0) {
            return false;
        }
        board[columnHeights[columnIndex]--][columnIndex] = color;
        countFilled++;
        return true;
    }

    public CellState getCellState(int row, int column) {
        return board[row][column];
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                result.append(board[i][j].getDisplay()).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    public boolean isDraw() {
        return countFilled == TOTAL_CELLS;
    }

//    public boolean isWon(int column) {
//        int row = columnHeights[column] + 1;
//        CellState currentPlayer = getCellState(row, column);
//        int count = 0;
//        for (int i = 0; i < COLUMNS; i++) {
//            if (currentPlayer == board[row][i])
//        }
//    }

    public boolean isWon(int column) {
        int row = columnHeights[column] + 1;
//        for (int i = ROWS - 1; i >= 0; i--) {
//            if (getCellState(i, column) == CellState.EMPTY) {
//                row = i;
//                break;
//            }
//        }
        CellState currentPlayer = getCellState(row, column);
        int consecutive = 1;
        for (int i = column - 1; i >= 0; i--) {
            if (getCellState(row, i) != currentPlayer) {
                break;
            }
            consecutive++;
        }
        for (int i = column + 1; i < COLUMNS; i++) {
            if (getCellState(row, i) != currentPlayer) {
                break;
            }
            consecutive++;
        }
        if (consecutive >= WIN_COUNT) {
            return true;
        }
        consecutive = 1;
//        for (int i = row - 1; i >= 0; i--) {
//            if (getCellState(i, column) != currentPlayer) {
//                break;
//            }
//            consecutive++;
//        }
        for (int i = row + 1; i < ROWS; i++) {
            if (getCellState(i, column) != currentPlayer) {
                break;
            }
            consecutive++;
        }
        if (consecutive >= WIN_COUNT) {
            return true;
        }

        // left-up to right-down
        consecutive = 1;
        for (int i = row - 1, j = column + 1; i >= 0 && j < COLUMNS; i--, j++) {
            if (getCellState(i, j) != currentPlayer) {
                break;
            }
            consecutive++;
        }
        for (int i = row + 1, j = column - 1; i < ROWS && j >= 0; i++, j--) {
            if (getCellState(i, j) != currentPlayer) {
                break;
            }
            consecutive++;
        }
        if (consecutive >= WIN_COUNT) {
            return true;
        }

        // left-down to right-up
        consecutive = 1;
        for (int i = row + 1, j = column + 1; i < ROWS && j < COLUMNS; i++, j++) {
            if (getCellState(i, j) != currentPlayer) {
                break;
            }
            consecutive++;
        }
        for (int i = row - 1, j = column - 1; i >= 0 && j >= 0; i--, j--) {
            if (getCellState(i, j) != currentPlayer) {
                break;
            }
            consecutive++;
        }
        if (consecutive >= WIN_COUNT) {
            return true;
        }
        return false;

    }

    public boolean removeAtColumn(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= COLUMNS || columnHeights[columnIndex] == ROWS - 1) {
            return false;
        }
        board[++columnHeights[columnIndex]][columnIndex] = CellState.EMPTY;
        countFilled--;
        return true;

//        int row = columnHeights[i] + 1;
//        board[row][i] = CellState.EMPTY;
//        columnHeights[i]++;
//        countFilled--;
    }

    public CellState[][] getGrid() {
        return board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board1 = (Board) o;

        return Arrays.deepEquals(board, board1.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }
}
