package uk.co.aosd.onto.reference;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.aosd.onto.foundation.Attribute;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;

/**
 * An implementation of the Attribute interface.
 *
 * @author Tony Walmsley
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeImpl<I extends Individual<? extends Event, ? extends Event>, P> implements Attribute<I, P> {
    private String identifier;
    private I individual;
    private P property;
    private Instant from;
    private Instant to;
}
