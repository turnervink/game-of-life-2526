package ca.bcit.comp2526.a2c;

import java.util.ArrayList;

/**
 * Represents an Omnivore, an Animal that
 * can eat plants and other species of Animals.
 *
 * @author Turner Vink - A01012232
 * @version 0.1
 */
public class Omnivore extends Animal {

    /**
     * Creates a Herbivore.
     *
     * @param location the Cell where the Herbivore is contained
     */
    public Omnivore(final Cell location) {
        super(location,
                HoldableType.OMNIVORE,
                Colors.OMNIVORE.getColor(),
                AnimalMetadata.OMNIVORE.getMaxHunger());
    }

    /**
     * Instantiates a new Omnivore.
     *
     * @param location the location of the new Animal
     * @return Omnivore
     */
    public Animal reproduce(final Cell location) {
        return new Omnivore(location);
    }

    /**
     * {@inheritDoc}
     */
    public int getMatesNeeded() {
        return AnimalMetadata.OMNIVORE.getMatesNeeded();
    }

    /**
     * {@inheritDoc}
     */
    public int getFoodNeeded() {
        return AnimalMetadata.OMNIVORE.getFoodNeeded();
    }

    /**
     * {@inheritDoc}
     */
    public int getSpaceNeeded() {
        return AnimalMetadata.OMNIVORE.getSpaceNeeded();
    }

    /**
     * {@inheritDoc}
     */
    public ArrayList<HoldableType> getCanEat() {
        return AnimalMetadata.OMNIVORE.getCanEat();
    }

}
