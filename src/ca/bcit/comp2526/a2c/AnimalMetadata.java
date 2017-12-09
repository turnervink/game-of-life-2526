package ca.bcit.comp2526.a2c;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Contains meta information on each species of Animal.
 * Its lifespan, required numbers of food, empty cell,
 * and mates for reproduction, and the types it can eat.
 *
 * @author Turner Vink - A01012232
 * @version 1.0
 */
public enum AnimalMetadata {
    /** Herbivore metadata. */
    HERBIVORE(10, 2, 2, 2, new ArrayList<>(
            Arrays.asList(HoldableType.PLANT))),
    /** Carnivore metadata. */
    CARNIVORE(7, 1, 2, 2, new ArrayList<>(
            Arrays.asList(HoldableType.HERBIVORE,
                          HoldableType.OMNIVORE))),
    /** Omnivore metadata. */
    OMNIVORE(2, 1, 3, 3, new ArrayList<>(
            Arrays.asList(HoldableType.PLANT,
                          HoldableType.HERBIVORE,
                          HoldableType.CARNIVORE)));

    /* The number of turns an Animal can go without eating. */
    private int maxHunger;
    /* The number of adjacent mates an Animal needs to reproduce. */
    private int matesNeeded;
    /* The number of adjacent edible things an Animal needs to reproduce. */
    private int foodNeeded;
    /* The number of adjacent EmptyCells an Animal needs to reproduce. */
    private int spaceNeeded;
    /* The HoldableTypes an Animal can eat. */
    private ArrayList<HoldableType> canEat;

    /**
     * Sets up enum values.
     *
     * @param maxHunger the number of turns an Animal can go without eating
     * @param matesNeeded the number of adjacent mates
     *                    an Animal needs to reproduce
     * @param foodNeeded the number of adjacent edible things
     *                   an Animal needs to reproduce
     * @param spaceNeeded the number of adjacent EmptyCells
     *                    an Animal needs to reproduce
     * @param canEat the HoldableTypes an Animal can eat
     */
    AnimalMetadata(int maxHunger, int matesNeeded,
                          int foodNeeded, int spaceNeeded,
                          ArrayList<HoldableType> canEat) {

        this.maxHunger = maxHunger;
        this.matesNeeded = matesNeeded;
        this.foodNeeded = foodNeeded;
        this.spaceNeeded = spaceNeeded;
        this.canEat = canEat;
    }

    /**
     * Gets the max hunger value for an Animal.
     *
     * @return int max hunger
     */
    public int getMaxHunger() {
        return maxHunger;
    }

    /**
     * Gets the needed mates for reproduction for an Animal.
     *
     * @return int needed mates
     */
    public int getMatesNeeded() {
        return matesNeeded;
    }

    /**
     * Gets the needed food for reproduction for an Animal.
     *
     * @return int needed food
     */
    public int getFoodNeeded() {
        return foodNeeded;
    }

    /**
     * Gets the needed EmptyCells for reproduction for an Animal.
     *
     * @return int needed EmptyCells
     */
    public int getSpaceNeeded() {
        return spaceNeeded;
    }

    /**
     * Gets the HoldableTypes edible by an Animal.
     *
     * @return ArrayList<HoldableType> edible types
     */
    public ArrayList<HoldableType> getCanEat() {
        return canEat;
    }
}
