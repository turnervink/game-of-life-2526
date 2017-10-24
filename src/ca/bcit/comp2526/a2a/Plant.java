package ca.bcit.comp2526.a2a;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a Plant that can grow by seeding into
 * adjacent Cells and be consumed by Animals.
 *
 * @author Turner Vink - A01012232
 * @version 0.1
 */
public class Plant extends Organism implements HerbivoreEdible {

    /* The minimum number of empty Cells that must be adjacent
     * to a Plant to allow it to seed.
     */
    private static final int MIN_EMPTY_NEIGHBOURS = 3;

    /**
     * Creates a Plant.
     *
     * @param location the Cell where the Plant is contained
     */
    public Plant(Cell location) {
        super(location, Colors.PLANT.getColor());
    }

    /**
     * Finds candidate Cells that the Plant can seed.
     * A Plant will only seed a neighbouring Cell if there are
     * at least 3 EmptyCells and 2 other Plants adjacent to it.
     * A Plant will only seed an EmptyCell. If multiple EmptyCells
     * are available, one is chosen at random.
     */
    public void takeAction() {
        this.setActionTaken(true);

        ArrayList<Cell> candidateEmptyCells = new ArrayList<>();
        ArrayList<Cell> candidatePlants = new ArrayList<>();

        for (Cell cell : this.getLocation().getNeighbours()) {
            Holdable contents = cell.getContents();
            if (contents instanceof EmptyCell) {
                candidateEmptyCells.add(cell);
            } else if (contents instanceof Plant) {
                candidatePlants.add(cell);
            }
        }

        if (candidateEmptyCells.size() >= MIN_EMPTY_NEIGHBOURS
            && candidatePlants.size() >= 2) {

            Random rand = new Random();
            int choice = rand.nextInt(candidateEmptyCells.size());

            Cell candidate = candidateEmptyCells.get(choice);

            this.seed(candidate);
        }
    }

    /**
     * Seeds a Cell by creating a new Plant and
     * placing it in it.
     *
     * @param candidate the Cell to seed
     */
    private void seed(Cell candidate) {
        candidate.setContents((new Plant(candidate)));
    }

    @Override
    public String toString() {
        return "Plant";
    }
}
