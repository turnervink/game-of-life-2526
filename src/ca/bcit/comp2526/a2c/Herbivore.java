package ca.bcit.comp2526.a2c;

import java.util.ArrayList;

/**
 * Represents an Herbivore, an Animal that only consumes
 * Plants.
 *
 * @author Turner Vink - A01012232
 * @version 0.2
 */
public class Herbivore extends Animal {

    /**
     * Creates a Herbivore.
     *
     * @param location the Cell where the Herbivore is contained
     */
    public Herbivore(final Cell location) {
        super(location, HoldableType.HERBIVORE,
                Colors.HERBIVORE.getColor(),
                AnimalMetadata.HERBIVORE.getMaxHunger());
    }

    /**
     * Instantiates a new Herbivore.
     *
     * @param location the location of the new Animal
     * @return Herbivore
     */
    public Animal reproduce(final Cell location) {
        return new Herbivore(location);
    }

    /**
     * {@inheritDoc}
     */
    public int getMatesNeeded() {
        return AnimalMetadata.HERBIVORE.getMatesNeeded();
    }

    /**
     * {@inheritDoc}
     */
    public int getFoodNeeded() {
        return AnimalMetadata.HERBIVORE.getFoodNeeded();
    }

    /**
     * {@inheritDoc}
     */
    public int getSpaceNeeded() {
        return AnimalMetadata.HERBIVORE.getSpaceNeeded();
    }

    /**
     * {@inheritDoc}
     */
    public ArrayList<HoldableType> getCanEat() {
        return AnimalMetadata.HERBIVORE.getCanEat();
    }
}
