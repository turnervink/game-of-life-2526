package ca.bcit.comp2526.a2a;

import java.awt.Color;

/**
 * Represents a generic Organism, which is something that
 * lives in a Cell in the World and can die.
 *
 * @author Turner Vink - A01012232
 * @version 0.1
 */
public abstract class Organism extends Holdable {

    /**
     * Creates an Organism.
     *
     * @param location the Cell where the Organism is located
     * @param color the Color the Cell containing the Organism should be
     */
    public Organism(final Cell location, final Color color) {
        super(location, color);
    }

    /**
     * Sets the location of the Organism.
     *
     * @param location the Cell to put the Organism in
     */
    public void setCell(final Cell location) {
        this.setLocation(location);
        location.setContents(this);
    }

    /**
     * Kills an Organism by setting its Cell contents
     * to an EmptyCell.
     */
    public void die() {
        this.getLocation().setContents(new EmptyCell(this.getLocation()));
    }

}
