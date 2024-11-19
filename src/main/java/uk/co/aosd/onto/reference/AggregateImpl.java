package uk.co.aosd.onto.reference;

import com.fasterxml.jackson.annotation.JsonTypeName;
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
@JsonTypeName("uk.co.aosd.onto.reference.aggregate")
public record AggregateImpl<N extends Number, U extends Unit, T>(String identifier, Class<T> kind, ScalarValue<N, U> quantity,
    Aggregated beginning, Disaggregated ending) implements Aggregate<N, U, T> {
}
