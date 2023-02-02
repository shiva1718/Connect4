import java.util.Scanner;

public class HumanPlayer extends Player {
    private String name;
    private CellState color;
    private Scanner sc = new Scanner(System.in);

    public HumanPlayer(String name, CellState color) {
        this.name = name;
        this.color = color;
    }

    public int makeMove(Board board) {
        try {
            System.out.print("Enter a column number to put your piece at: ");
            return sc.nextInt();
        } catch (Exception e) {
            System.out.println();
            System.out.println("Invalid input. Please try again.");
            return makeMove(board);
        }
    }

    public CellState getColor() {
        return color;
    }

    public String toString() {
        return color.getDisplay();
    }

}
