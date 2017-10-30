package ca.bcit.comp2526.a2a;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents an Herbivore, an Animal that only consumes
 * objects that are HerbivoreEdible. If a Herbivore does not
 * eat for more than 10 turns, it will die.
 *
 * @author Turner Vink - A01012232
 * @version 0.1
 */
public class Herbivore extends Animal {

    /* The number of turns a Herbivore can go without eating. */
    private static final int MAX_HUNGER = 10;

    /**
     * Creates a Herbivore.
     *
     * @param location the Cell where the Herbivore is contained
     */
    public Herbivore(final Cell location) {
        super(location, Colors.HERBIVORE.getColor(), MAX_HUNGER);
    }

    /**
     * Moves the Herbivore to a new Cell.
     *
     * @param dest the Cell to move the Herbivore to
     */
    private void move(final Cell dest) {
        this.die();
        this.setCell(dest);
    }

    /**
     * Finds candidate neighbouring Cells for the Herbivore
     * to move into. If multiple candidates are found, one is
     * selected at random. If the selected Cell contains an
     * object that is edible by a Herbivore, it is eaten
     * by the Herbivore.
     */
    public void takeAction() {
        this.setActionTaken(true);
        this.increaseHunger();

        if (this.hasStarved()) {
            this.die();
        } else {
            ArrayList<Cell> candidateEmptyCells = new ArrayList<>();
            ArrayList<Cell> candidateHerbivoreEdibles = new ArrayList<>();
            Random rand = new Random();

            for (Cell cell : this.getLocation().getNeighbours()) {
                Holdable contents = cell.getContents();
                if (contents instanceof EmptyCell) {
                    candidateEmptyCells.add(cell);
                } else if (contents instanceof HerbivoreEdible) {
                    candidateHerbivoreEdibles.add(cell);
                }
            }

            // Prioritize a Plant over an EmptyCell
            if (candidateHerbivoreEdibles.size() > 0) {
                int choice = rand.nextInt(candidateHerbivoreEdibles.size());

                Cell candidate = candidateHerbivoreEdibles.get(choice);

                this.move(candidate);
                this.resetHunger(); // "Eat" after moving onto a Plant
            } else if (candidateEmptyCells.size() > 0) {
                int choice = rand.nextInt(candidateEmptyCells.size());

                Cell candidate = candidateEmptyCells.get(choice);

                this.move(candidate);
            }
        }
    }
}
