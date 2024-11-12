package uk.co.aosd.onto.foundation;

import uk.co.aosd.onto.events.Aggregated;
import uk.co.aosd.onto.events.Disaggregated;

/**
 * A quantity of similar things considered as a single whole object, e.g. a pile
 * of sand or a collection of water molecules in a glass of water.
 *
 * @author Tony Walmsley
 */
public interface Aggregate<T extends Number, U extends Unit> extends Individual<Aggregated, Disaggregated> {
    ScalarValue<T, U> quantity();
}
