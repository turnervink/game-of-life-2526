package ca.bcit.comp2526.a2c;

import java.util.ArrayList;

/**
 * Represents a Carnivore, an Animal
 * that only eats other species of Animals.
 *
 * @author Turner Vink - A01012232
 * @version 0.1
 */
public class Carnivore extends Animal {

    /**
     * Creates a Herbivore.
     *
     * @param location the Cell where the Herbivore is contained
     */
    public Carnivore(final Cell location) {
        super(location, HoldableType.CARNIVORE,
                Colors.CARNIVORE.getColor(),
                AnimalMetadata.CARNIVORE.getMaxHunger());
    }

    /**
     * Instantiates a new Carnivore.
     *
     * @param location the location of the new Animal
     * @return Carnivore
     */
    public Animal reproduce(final Cell location) {
        return new Carnivore(location);
    }

    /**
     * {@inheritDoc}
     */
    public int getMatesNeeded() {
        return AnimalMetadata.CARNIVORE.getMatesNeeded();
    }

    /**
     * {@inheritDoc}
     */
    public int getFoodNeeded() {
        return AnimalMetadata.CARNIVORE.getFoodNeeded();
    }

    /**
     * {@inheritDoc}
     */
    public int getSpaceNeeded() {
        return AnimalMetadata.CARNIVORE.getSpaceNeeded();
    }

    /**
     * {@inheritDoc}
     */
    public ArrayList<HoldableType> getCanEat() {
        return AnimalMetadata.CARNIVORE.getCanEat();
    }
}
