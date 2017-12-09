package ca.bcit.comp2526.a2c;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.Timer;

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
public class World implements Serializable {
    private static final long serialVersionUID = 1473082296926965659L;

    /* Array of Cells in the World. */
    private Cell[][] grid;
    /* Number of columns in the World. */
    private int columns;
    /* Number of rows in the World. */
    private int rows;
    /* Number of turns taken. */
    private int turns;
    /* The chance that a Cell will contain a Herbivore. */
    private final int herbivoreChance = 80;
    /* The chance that a Cell will contain a Plant. */
    private final int plantChance = 50;
    /* The chance that a Cell will contain a Carnivore. */
    private final int carnivoreChance = 40;
    /* The chance that a Cell will contain an Omnivore. */
    private final int omnivoreChance = 32;
    /* 100%. */
    private final int maxChance = 100;
    /* The delay between turns in ms. */
    private final int turnDelay = 100;
    /* Timer for running the World. */
    private Timer timer;

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
        this.turns = 0;
        this.timer = new Timer(turnDelay, new WorldListener());
    }

    /**
     * Initializes the World by filling it with Cells,
     * and then based on a random chance filling those
     * Cells with certain Holdable objects.
     *
     * @throws CouldNotAddException if the neighbours for a Cell
     *                              could not be retrieved
     */
    public void init() throws CouldNotAddException {
        RandomGenerator.reset();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = new Cell(this, i, j);

                int chance = RandomGenerator.nextNumber(maxChance);
                if (chance > herbivoreChance) {
                    grid[i][j].setContents(new Herbivore(grid[i][j]));
                } else if (chance > plantChance) {
                    grid[i][j].setContents(new Plant(grid[i][j]));
                } else if (chance > carnivoreChance) {
                    grid[i][j].setContents(new Carnivore(grid[i][j]));
                } else if (chance > omnivoreChance) {
                    grid[i][j].setContents(new Omnivore(grid[i][j]));
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
     * @return int number of rows
     */
    public int getRowCount() {
        return rows;
    }

    /**
     * Gets the number of columns in the World.
     *
     * @return int number of columns
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
     * @return Cell the desired Cell
     */
    public Cell getCellAt(int row, int column) {
        return grid[row][column];
    }

    /**
     * Gets the number of turns the World has taken.
     *
     * @return int number of turns taken
     */
    public int getTurns() {
        return turns;
    }

    /**
     * Checks to see if a row/column coordinate
     * is within the bounds of the World.
     *
     * @param x row
     * @param y column
     * @return boolean true if the row/column coordinate
     *         is within the World bounds
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
        this.turns++;

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

    /**
     * Starts the timer for the World.
     */
    public void start() {
        timer.start();
    }

    /**
     * Stops the timer for the World.
     */
    public void stop() {
        timer.stop();
    }

    /**
     * Listener for timer events.
     */
    private class WorldListener implements ActionListener, Serializable {
        /**
         * Has the World take a turn on each timer event.
         *
         * @param e the event that occurred
         */
        public void actionPerformed(final ActionEvent e) {
            takeTurn();
        }
    }
}


