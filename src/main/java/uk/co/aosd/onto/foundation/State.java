package uk.co.aosd.onto.foundation;

/**
 * A temporal part of an individual.
 *
 * @author Tony Walmsley
 */
public interface State<B extends Event, E extends Event, V extends Individual<?, ?>> extends EventBounded<B, E>, UniquelyIdentifiable {
    V getIndividual();
}
