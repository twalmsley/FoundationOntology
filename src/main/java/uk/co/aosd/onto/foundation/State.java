package uk.co.aosd.onto.foundation;

/**
 * A temporal part of an individual.
 *
 * @author Tony Walmsley
 */
public interface State<V extends Individual> extends EventBounded, UniquelyIdentifiable {
    V individual();
}
