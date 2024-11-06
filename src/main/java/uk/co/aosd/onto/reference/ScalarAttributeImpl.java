package uk.co.aosd.onto.reference;

import java.time.Instant;
import java.util.Optional;

import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.ScalarAttribute;
import uk.co.aosd.onto.foundation.ScalarValue;
import uk.co.aosd.onto.foundation.Unit;

/**
 * An implementation of the ScalarAttribute interface.
 *
 * @author Tony Walmsley
 */
public record ScalarAttributeImpl<I extends Individual, N extends Number, U extends Unit>(I individual,
    ScalarValue<N, U> property, Optional<Instant> beginning, Optional<Instant> ending)
    implements ScalarAttribute<I, N, U> {

}
