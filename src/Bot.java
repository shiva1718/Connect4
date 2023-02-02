import java.util.HashMap;
import java.util.Map;

public class Bot extends Player {

    // implement minimax algorithm for connect4 game
    private CellState color;
    private int maxDepth;
    private Map<Board, Double> memo;
    public Bot(CellState color, int maxDepth) {
        this.color = color;
        this.maxDepth = maxDepth;
        memo = new HashMap<>();
    }

    public int makeMove(Board board) {
//        int bestMove = 0;
//        int bestScore = Integer.MIN_VALUE;
//        for (int i = 0; i < Board.COLUMNS; i++) {
//            if (board.insertAtColumn(i, CellState.RED)) {
//                int score = minimax(board, 0, false);
//                board.removeAtColumn(i);
//                if (score > bestScore) {
//                    bestScore = score;
//                    bestMove = i;
//                }
//            }
//        }
//        return bestMove;
        double bestScore;
        bestScore = Integer.MIN_VALUE;
        int bestMove = 0;
        for (int i = 0; i < Board.COLUMNS; i++) {
            if (board.insertAtColumn(i, color)) {
                if (board.isWon(i)) {
                    board.removeAtColumn(i);
                    return i;
                }
                if (board.isDraw()) {
                    board.removeAtColumn(i);
                    if (bestScore < 0) {
                        bestScore = 0;
                        bestMove = i;
                    }
                } else {
                    double score = minimax(board, color.switchColor(), 0);
                    board.removeAtColumn(i);
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = i;
                    }
                }
            }
            System.out.println("Calculated " + i);
        }
        System.out.println("Best move score: " + bestScore);
        return bestMove;
    }

    // implement minimax algorithm for connect4 game
    private double minimax(Board board, CellState cur, int depth) {
        if (memo.containsKey(board)) {
            return memo.get(board);
        }
        double stateScore;
        if (cur == color) {
            stateScore = Integer.MIN_VALUE;
        } else {
            stateScore = Integer.MAX_VALUE;
        }
        for (int i = 0; i < Board.COLUMNS; i++) {
            if (board.insertAtColumn(i, cur)) {
                if (board.isWon(i)) {
                    board.removeAtColumn(i);
                    return cur == color ? 10_000_000 : -10_000_000;
                }
                if (board.isDraw()) {
                    board.removeAtColumn(i);
                    return 0;
                }
                double score = minimax(board, cur.switchColor(), depth + 1);
                if (!board.removeAtColumn(i)) {
                    System.out.println("UNABLE TO REMOVE:");
                    System.out.println(board);
                    System.out.println("COLUMN: " + i);
                    System.out.println("DEPTH: " + depth);
                }
                if (cur == color) {
                    stateScore = Math.max(stateScore, score);
                } else {
                    stateScore = Math.min(stateScore, score);
                }
            }
        }
        if (stateScore == Integer.MAX_VALUE || stateScore == Integer.MIN_VALUE) {
            System.out.println("state score is max or min value{Invalid}");
            System.out.println(board);
        }
        if (stateScore < 0) {
            stateScore++;
        } else {
            stateScore--;
        }
        memo.put(new Board(board), stateScore);
        return stateScore;
    }

    public CellState getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Bot: " + color;
    }
}
