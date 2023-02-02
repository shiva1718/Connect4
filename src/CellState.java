public enum CellState {
    EMPTY(0, "-"),
    RED(1, "R"),
    YELLOW(-1, "Y");

    private final int value;
    private final String display;

    CellState(int value, String display) {
        this.value = value;
        this.display = display;
    }

    public int getValue() {
        return value;
    }

    public String getDisplay() {
        return display;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public CellState switchColor() {
        return this == RED ? YELLOW : RED;
    }


}
