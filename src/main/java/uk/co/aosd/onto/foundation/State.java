package uk.co.aosd.onto.foundation;

/**
 * A temporal part of an individual.
 *
 * @author Tony Walmsley
 */
public interface State<T extends Number, U extends Unit, V extends Individual<T, U>> extends TemporallyBounded<T, U>, UniquelyIdentifiable {
    V individual();
}
