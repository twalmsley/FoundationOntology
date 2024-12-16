package uk.co.aosd.onto.foundation;

import uk.co.aosd.onto.events.Aggregated;
import uk.co.aosd.onto.events.Disaggregated;

/**
 * A quantity of similar things considered as a single whole object, e.g. a pile
 * of sand or a collection of water molecules in a glass of water.
 *
 * @author Tony Walmsley
 */
public interface Aggregate<N extends Number, U extends Unit, T, V extends Aggregated, W extends Disaggregated> extends Individual<V, W> {
    ScalarValue<N, U> getQuantity();

    /**
     * The kind of material in the aggregate.
     *
     * @return Class representing the kind of material.
     */
    java.lang.Class<T> getKind();
}
