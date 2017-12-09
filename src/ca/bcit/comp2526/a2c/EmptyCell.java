package ca.bcit.comp2526.a2c;

import java.io.Serializable;

/**
 * Used for a Cell with no contents.
 *
 * @author Turner Vink - A01012232
 * @version 0.1
 */
public class EmptyCell extends Holdable implements Serializable {

    /**
     * Creates an EmptyCell.
     *
     * @param location the Cell where the object is contained
     */
    public EmptyCell(final Cell location) {
        super(location, HoldableType.EMPTY_CELL, Colors.EMPTY.getColor());
    }

    /**
     * Unused.
     */
    public void takeAction() {
        this.setActionTaken(true);
    }
}
