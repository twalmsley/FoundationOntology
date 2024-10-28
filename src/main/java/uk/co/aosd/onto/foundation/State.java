package uk.co.aosd.onto.foundation;

/**
 * A temporal part of an individual.
 */
public interface State<T extends Number, U extends Unit, V extends UniquelyIdentifiable> extends TemporallyBounded<T, U>, UniquelyIdentifiable {
    V individual();
}
