package ca.bcit.comp2526.a2c;

import java.awt.Color;

/**
 * Colours for each type of Holdable object.
 *
 * @author Turner Vink - A01012232
 * @version 0.1
 */
public enum Colors {
    /** Colour of a Plant. */
    PLANT(Color.GREEN),
    /** Colour of a Herbivore. */
    HERBIVORE(Color.YELLOW),
    /** Colour of a Carnivore. */
    CARNIVORE(Color.MAGENTA),
    /** Colour of a Carnivore. */
    OMNIVORE(Color.BLUE),
    /** Color of an EmptyCell. */
    EMPTY(Color.WHITE);

    private Color color;

    /**
     * Sets up enum values.
     *
     * @param color the colour for the Holdable type
     */
    Colors(final Color color) {
        this.color = color;

    }

    /**
     * Gets the colour of a Holdable type.
     *
     * @return Color
     */
    public Color getColor() {
        return color;
    }
}
