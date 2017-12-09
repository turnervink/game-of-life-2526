package ca.bcit.comp2526.a2c;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.io.Serializable;

/**
 * Represents a Cell in a World. Contains an object
 * of type Holdable that will take an action on each
 * World turn.
 *
 * @author Turner Vink - A01012232
 * @version 0.1
 */
public class Cell extends JPanel implements Serializable {

    private static final long serialVersionUID = 5483123527347019750L;

    /* Used with the hash() method. */
    private final int hashValue = 31;
    /* The width and height for a Cell in the GameFrame. */
    private final int sideLength = 15;
    /* The contents of the Cell. */
    private Holdable contents;
    /* The World the Cell is in. */
    private transient World world;
    /* The row the Cell is in. */
    private int row;
    /* The column the Cell is in. */
    private int column;
    /* The Cells surrounding the Cell. */
    private transient DoubleLinkedList<Cell> neighbours;

    /**
     * Creates a Cell.
     *
     * @param world the World the Cell is in
     * @param row the row number of the Cell
     * @param column the column number of the Cell
     */
    public Cell(final World world, int row, int column) {
        this.world = world;
        this.row = row;
        this.column = column;

        this.init();
        this.setContents(new EmptyCell(this));
    }

    /**
     * Gets the contents of the Cell.
     *
     * @return Holdable an object of type Holdable
     */
    public Holdable getContents() {
        return contents;
    }

    /**
     * Sets the contents of the Cell.
     *
     * @param newContents the Holdable object to place in the Cell
     */
    public void setContents(final Holdable newContents) {
        this.contents = newContents;
        this.contents.init();
    }

    /**
     * Gets the row number of the Cell.
     *
     * @return int row number of the Cell
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column number of the Cell.
     *
     * @return int column number of the Cell
     */
    public int getColumn() {
        return column;
    }

    /**
     * Sets the World the Cell is in. Used when
     * loading a saved World.
     *
     * @param world World the world the cell is in
     */
    public void setWorld(final World world) {
        this.world = world;
    }

    /**
     * Finds and stores the Cell's neighbours.
     *
     * @throws CouldNotAddException if Cell cannot be added to neighbours
     */
    public void initNeighbours() throws CouldNotAddException {
        this.neighbours = getAdjacentCells();
    }

    /**
     * Gets an array of the Cell's neighbours.
     *
     * @return Cell[] array of Cells
     */
    public DoubleLinkedList<Cell> getNeighbours() {
        return this.neighbours;
    }

    /**
     * Initializes a Cell by setting its width and height in the frame
     * and drawing a border.
     */
    public void init() {
        setSize(new Dimension(this.sideLength, this.sideLength));
        setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.BLACK));
    }

    /**
     * Debugging method. Draws the coordinates of the Cell.
     *
     * @param g graphics context to draw on
     */
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
//        g.drawString("" + row + ", " + column, 0, 10);
    }

    /**
     * Gets the Cells adjacent to the Cell.
     *
     * @throws CouldNotAddException if Cell could not be added to neighbours
     * @return array of adjacent Cells
     */
    private DoubleLinkedList<Cell> getAdjacentCells()
            throws CouldNotAddException {
        DoubleLinkedList<Cell> adjacent = new DoubleLinkedList<>();

        for (int i = this.row - 1; i <= this.row + 1; i++) {
            for (int j = this.column - 1; j <= this.column + 1; j++) {
                if (this.world.inWorld(i, j)
                        && !(this.equals(world.getCellAt(i, j)))) {

                    adjacent.addToEnd(world.getCellAt(i, j));
                }
            }
        }

        return adjacent;
    }

    @Override
    public String toString() {
        return ("Cell @ (" + this.row + ", " + this.column + ")");
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Cell)) {
            return false;
        }

        return (((Cell) obj).getRow() == this.row
                && ((Cell) obj).getColumn() == this.column);
    }

    @Override
    public int hashCode() {
        int result = world != null ? world.hashCode() : 0;
        result = this.hashValue * result + row;
        result = this.hashValue * result + column;
        return result;
    }
}
