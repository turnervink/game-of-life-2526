package ca.bcit.comp2526.a2c;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents an Animal, which is an Organism that
 * can move from Cell to Cell and must eat every
 * certain amount of turns to stay alive. Animals
 * can reproduce and create a new instance of their
 * species when certain conditions are met.
 *
 * @author Turner Vink - A01012232
 * @version 0.2
 */
public abstract class Animal extends Organism {

    /* How many turns the Animal can go without eating */
    private int remainingHunger;
    /* The number of turns an Animal can go without eating before dying. */
    private int maxHunger;

    /**
     * Creates an Animal.
     *
     * @param location the Cell where the Animal is contained
     * @param type the HoldableType of the Animal
     * @param color the color of the Animal
     * @param maxHunger the number of turns the Animal can go
     *                  without eating
     */
    public Animal(final Cell location, final HoldableType type,
                  final Color color, int maxHunger) {
        super(location, type, color);
        this.maxHunger = maxHunger;
        this.remainingHunger = maxHunger;
    }

    /**
     * Gets the number of adjacent mates a species of Animal
     * needs to reproduce.
     *
     * @return int number of mates needed
     */
    public abstract int getMatesNeeded();

    /**
     * Gets the amount of adjacent food a species of Animal
     * needs to reproduce.
     *
     * @return int amount of food needed
     */
    public abstract int getFoodNeeded();

    /**
     * Gets the number of adjacent EmptyCells a species of Animal
     * needs to reproduce.
     *
     * @return int number of EmptyCells needed
     */
    public abstract int getSpaceNeeded();

    /**
     * Gets the HoldableTypes a species of Animal can eat.
     *
     * @return ArrayList<HoldableType> types edible by this species
     */
    public abstract ArrayList<HoldableType> getCanEat();

    /**
     * Checks if the Animal has starved.
     *
     * @return true if <code>remainingHunger</code> is 0
     */
    private boolean hasStarved() {
        return remainingHunger == 0;
    }

    /**
     * Decrements the Animal's remaining hunger.
     */
    private void increaseHunger() {
        remainingHunger--;
    }

    /**
     * Resets the Animal's hunger to the correct
     * value for the type of Animal.
     */
    private void resetHunger() {
        remainingHunger = this.maxHunger;
    }

    /**
     * Chooses a Cell for the Animal to move to.
     *
     * @param menu the HoldableTypes the Animal can eat
     * @return Cell the Cell to move to
     */
    private Cell getDestination(final ArrayList<HoldableType> menu) {
        ArrayList<Cell> food = new ArrayList<>();
        ArrayList<Cell> empty = new ArrayList<>();

        for (Cell cell : this.getLocation().getNeighbours()) {
            Holdable contents = cell.getContents();
            if (menu.contains(contents.getType())) {
                food.add(cell);
            } else if (contents.getType().equals(HoldableType.EMPTY_CELL)) {
                empty.add(cell);
            }
        }

        Random rand = new Random();

        if (food.size() > 0) {
            return food.get(rand.nextInt(food.size()));
        } else if (empty.size() > 0) {
            return empty.get(rand.nextInt(empty.size()));
        } else {
            return null;
        }
    }

    /**
     * Moves the Animal to a new Cell.
     *
     * @param dest the Cell to move the Animal to
     */
    private void move(final Cell dest) {
        this.die();
        this.setCell(dest);
    }

    /**
     * Checks to see if the conditions to give birth are met.
     *
     * @return boolean true if the conditions are met
     */
    private boolean canGiveBirth() {

        int numMates = 0;
        int numFood = 0;
        int numSpace = 0;

        for (Cell cell : this.getLocation().getNeighbours()) {
            Holdable contents = cell.getContents();
            if (this.getCanEat().contains(contents.getType())) {
                numFood++;
            } else if (contents.getType().equals(this.getType())) {
                numMates++;
            } else if (contents.getType().equals(HoldableType.EMPTY_CELL)) {
                numSpace++;
            }
        }

        return numFood >= this.getFoodNeeded()
                && numMates >= this.getMatesNeeded()
                && numSpace >= this.getSpaceNeeded();
    }

    /**
     * Creates a new instance of the Animal's species
     * and places it in an adjacent EmptyCell.
     */
    private void giveBirth() {
        ArrayList<Cell> empty = new ArrayList<>();

        for (Cell cell : this.getLocation().getNeighbours()) {
            Holdable contents = cell.getContents();
            if (contents.getType().equals(HoldableType.EMPTY_CELL)) {
                empty.add(cell);
            }
        }

        Random rand = new Random();
        Cell birthplace = empty.get(rand.nextInt(empty.size()));
        Animal baby = this.reproduce(birthplace);
        birthplace.setContents(baby);
        baby.setActionTaken(true);
    }

    /**
     * Instantiates a new object of a species of Animal.
     *
     * @param location the location of the new Animal
     * @return Animal the new Animal
     */
    public abstract Animal reproduce(Cell location);

    /**
     * Causes an Animal to take an action on a turn of the World.
     */
    public void takeAction() {
        this.setActionTaken(true);
        this.increaseHunger();

        if (this.hasStarved()) {
            this.die();
        } else {
            Cell destination = this.getDestination(this.getCanEat());

            if (destination != null) {
                if (this.getCanEat().contains(
                                     destination.getContents().getType())) {
                    this.resetHunger();
                }

                this.move(destination);
            }

            if (this.canGiveBirth()) {
                this.giveBirth();
            }
        }
    }

}
