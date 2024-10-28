package uk.co.aosd.onto.foundation;

/**
 * An object considered as a single thing, even though it is made up of parts.
 * E.g. a person, a car, etc.
 */
public interface Individual<T extends Number, U extends Unit> extends TemporallyBounded<T, U>, UniquelyIdentifiable {

}
