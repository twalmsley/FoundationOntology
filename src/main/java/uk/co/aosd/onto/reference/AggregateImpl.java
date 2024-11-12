package uk.co.aosd.onto.reference;

import uk.co.aosd.onto.events.Aggregated;
import uk.co.aosd.onto.events.Disaggregated;
import uk.co.aosd.onto.foundation.Aggregate;
import uk.co.aosd.onto.foundation.ScalarValue;
import uk.co.aosd.onto.foundation.Unit;

/**
 * An implementation of the Aggregation interface.
 *
 * @author Tony Walmsley
 */
public record AggregateImpl<T extends Number, U extends Unit>(String identifier, ScalarValue<T, U> quantity,
    Aggregated beginning, Disaggregated ending) implements Aggregate<T, U> {

}
