package ca.bcit.comp2526.a2a;

/**
 * Used for a Cell with no contents.
 *
 * @author Turner Vink - A01012232
 * @version 0.1
 */
public class EmptyCell extends Holdable {

    /**
     * Creates an EmptyCell.
     *
     * @param location the Cell where the object is contained
     */
    public EmptyCell(final Cell location) {
        super(location, Colors.EMPTY.getColor());
    }

    /**
     * Unused.
     */
    public void takeAction() {
        this.setActionTaken(true);
    }

    @Override
    public String toString() {
        return "EmptyCell";
    }
}
