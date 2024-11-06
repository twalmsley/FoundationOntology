package uk.co.aosd.onto.reference;

import java.time.Instant;
import java.util.Optional;

import uk.co.aosd.onto.foundation.Aggregation;
import uk.co.aosd.onto.foundation.ScalarValue;
import uk.co.aosd.onto.foundation.Unit;

/**
 * An implementation of the Aggregation interface.
 *
 * @author Tony Walmsley
 */
public record AggregationImpl<T extends Number, U extends Unit>(String identifier, ScalarValue<T, U> quantity,
    Optional<Instant> beginning, Optional<Instant> ending) implements Aggregation<T, U> {

}
