package uk.co.aosd.onto.reference;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonTypeName;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.ScalarAttribute;
import uk.co.aosd.onto.foundation.ScalarValue;
import uk.co.aosd.onto.foundation.Unit;

/**
 * An implementation of the ScalarAttribute interface.
 *
 * @author Tony Walmsley
 */
@JsonTypeName("uk.co.aosd.onto.reference.scalarattribute")
public record ScalarAttributeImpl<I extends Individual<? extends Event, ? extends Event>, N extends Number, U extends Unit>(I individual,
    ScalarValue<N, U> property, Instant from, Instant to) implements ScalarAttribute<I, N, U> {

}
