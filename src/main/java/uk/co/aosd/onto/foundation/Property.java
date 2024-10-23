package uk.co.aosd.onto.foundation;

/**
 * A set of uniquely identifiable things with a given property U.
 * The property could be Colour, for example, but not a numeric value since that would be a ScalarPropery.
 */
public interface Property<T extends UniquelyIdentifiable, U> extends Class<T> {
    U property();
}
