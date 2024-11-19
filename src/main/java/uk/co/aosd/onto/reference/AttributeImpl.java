package uk.co.aosd.onto.reference;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonTypeName;
import uk.co.aosd.onto.foundation.Attribute;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;

/**
 * An implementation of the Attribute interface.
 *
 * @author Tony Walmsley
 */
@JsonTypeName("uk.co.aosd.onto.reference.attribute")
public record AttributeImpl<I extends Individual<? extends Event, ? extends Event>, P>(I individual, P property, Instant from,
    Instant to) implements Attribute<I, P> {

}
