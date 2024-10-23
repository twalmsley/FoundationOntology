package uk.co.aosd.onto.foundation;

/**
 * A temporal part of an individual.
 */
public interface State<T extends UniquelyIdentifiable> extends TemporallyBounded, UniquelyIdentifiable {
    T individual();
}
