package ca.bcit.comp2526.a2a;

import java.awt.Color;

/**
 * Represents an Animal, which is an Organism that
 * can move from Cell to Cell and must eat every
 * certain amount of turns to stay alive.
 *
 * @author Turner Vink - A01012232
 * @version 0.1
 */
public abstract class Animal extends Organism {

    /* The maximum hunger lever of the Animal */
    private final int maxHunger;
    /* How many turns the Animal can go without eating */
    private int remainingHunger;

    /**
     * Creates an Animal.
     *
     * @param location the Cell where the Animal is contained
     * @param color the color of the Animal
     * @param maxHunger the number of turns the Animal can go
     *                      without eating
     */
    public Animal(final Cell location, final Color color, int maxHunger) {
        super(location, color);
        this.maxHunger = maxHunger;
        this.remainingHunger = maxHunger;
    }

    /**
     * Checks if the Animal has starved.
     *
     * @return true if <code>remainingHunger</code> is 0
     */
    public boolean hasStarved() {
        return remainingHunger == 0;
    }

    /**
     * Decrements the Animal's remaining hunger.
     */
    public void increaseHunger() {
        remainingHunger--;
    }

    /**
     * Resets the Animal's hunger to the correct
     * value for the type of Animal.
     */
    public void resetHunger() {
        remainingHunger = this.maxHunger;
    }

}
