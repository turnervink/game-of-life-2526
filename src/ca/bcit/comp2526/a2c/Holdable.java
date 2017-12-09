package ca.bcit.comp2526.a2c;

import java.awt.Color;
import java.io.Serializable;

/**
 * Represents an object that can be contained in a Cell.
 *
 * @author Turner Vink - A01012232
 * @version 0.1
 */
public abstract class Holdable implements Serializable {
    private static final long serialVersionUID = 4815117119508704886L;

    /* The Cell the Holdable is contained in. */
    private transient Cell location;
    /* The Color the Holdable is displayed in. */
    private Color color;
    /* Indicates whether a Holdable has already taken its action. */
    private boolean actionTaken;
    /* The type of the Holdable. */
    private HoldableType type;

    /**
     * Creates a Holdable object that can be contained in a Cell.
     *
     * @param location the Cell where the object is contained
     * @param type the HoldableType of the Holdable
     * @param color the Color of the object
     */
    public Holdable(final Cell location, final HoldableType type,
                    final Color color) {
        this.location = location;
        this.color = color;
        this.type = type;
    }

    /**
     * Initializes a Holdable object by setting
     * the colour of its Cell to the correct colour
     * for its type.
     */
    public void init() {
        this.location.setBackground(color);
    }

    /**
     * Called each time the World takes a turn.
     * Executes the specific actions required for each
     * object contained in a Cell.
     */
    public abstract void takeAction();

    /**
     * Gets the location of the Holdable object.
     *
     * @return Cell the Cell containing the object
     */
    public Cell getLocation() {
        return location;
    }

    /**
     * Sets the location of the Holdable object.
     *
     * @param location the Cell where the object is located
     */
    public void setLocation(final Cell location) {
        this.location = location;
    }

    /**
     * Checks to see if a Holdable object has
     * already taken its turn.
     *
     * @return boolean true if a turn has been taken
     */
    public boolean isActionTaken() {
        return actionTaken;
    }

    /**
     * Sets whether a Holdable object has
     * taken its turn.
     *
     * @param actionTaken the state of actionTaken to set
     */
    public void setActionTaken(boolean actionTaken) {
        this.actionTaken = actionTaken;
    }

    /**
     * Gets the type of the Holdable.
     *
     * @return HoldableType
     */
    public HoldableType getType() {
        return type;
    }

    @Override
    public String toString() {
        return this.getType().toString();
    }
}
