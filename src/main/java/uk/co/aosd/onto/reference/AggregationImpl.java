package uk.co.aosd.onto.reference;

import uk.co.aosd.onto.foundation.Aggregation;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.ScalarValue;
import uk.co.aosd.onto.foundation.Unit;

/**
 * An implementation of the Aggregation interface.
 *
 * @author Tony Walmsley
 */
public record AggregationImpl<T extends Number, U extends Unit>(String identifier, ScalarValue<T, U> quantity,
    Event beginning, Event ending) implements Aggregation<T, U> {

}
