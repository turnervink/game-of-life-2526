package ca.bcit.comp2526.a2a;

/**
 * Represents a World where Cells are placed
 * that contain Holdable objects. Each time the
 * mouse is clicked, the World takes a turn
 * and iterates through its Cells to have their
 * contents perform their specific actions.
 *
 * @author Turner Vink - A01012232
 * @version 0.1
 */
public class World {
    /* Array of Cells in the World. */
    private Cell[][] grid;
    /* Number of columns in the World. */
    private int columns;
    /* Number of rows in the World. */
    private int rows;
    /* The chance that a Cell will contain a Herbivore. */
    private final int HERBIVORE_CHANCE = 80;
    /* The chance that a Cell will contain a Plant. */
    private final int PLANT_CHANCE = 50;
    /* 100%. */
    private final int MAX_CHANCE = 100;

    /**
     * Creates a World.
     *
     * @param rows the number of rows
     * @param columns the number of columns
     */
    public World(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new Cell[rows][columns];
    }

    /**
     * Initializes the World by filling it with Cells,
     * and then based on a random chance filling those
     * Cells with certain Holdable objects.
     */
    public void init() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = new Cell(this, i, j);

                int chance = RandomGenerator.nextNumber(MAX_CHANCE);
                if (chance > HERBIVORE_CHANCE) {
                    grid[i][j].setContents(new Herbivore(grid[i][j]));
                } else if (chance > PLANT_CHANCE) {
                    grid[i][j].setContents(new Plant(grid[i][j]));
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j].initNeighbours();
            }
        }
    }

    /**
     * Gets the number of rows in the World.
     *
     * @return number of rows
     */
    public int getRowCount() {
        return rows;
    }

    /**
     * Gets the number of columns in the World.
     *
     * @return number of columns
     */
    public int getColumnCount() {
        return columns;
    }

    /**
     * Gets a Cell at a specified row/column
     * position in the World.
     *
     * @param row the row of the Cell
     * @param column the column of the Cell
     * @return the desired Cell
     */
    public Cell getCellAt(int row, int column) {
        return grid[row][column];
    }

    /**
     * Checks to see if a row/column coordinate
     * is within the bounds of the World.
     *
     * @param x row
     * @param y column
     * @return true if the row/column coordinate is within the World bounds
     */
    public boolean inWorld(int x, int y) {
        return ((x >= 0 && x < this.getRowCount())
                && (y >= 0 && y < this.getColumnCount()));
    }

    /**
     * Advances the World one turn.
     * Iterates through all Cells in the World
     * and has their contents take their
     * specified action.
     */
    public void takeTurn() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!grid[i][j].getContents().isActionTaken()) {
                    grid[i][j].getContents().takeAction();
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j].getContents().setActionTaken(false);
            }
        }
    }
}
