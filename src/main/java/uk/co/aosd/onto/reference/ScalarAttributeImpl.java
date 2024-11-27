package uk.co.aosd.onto.reference;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScalarAttributeImpl<I extends Individual<? extends Event, ? extends Event>, N extends Number, U extends Unit> implements ScalarAttribute<I, N, U> {
    private String identifier;
    private I individual;
    private ScalarValue<N, U> property;
    private Instant from;
    private Instant to;
}
