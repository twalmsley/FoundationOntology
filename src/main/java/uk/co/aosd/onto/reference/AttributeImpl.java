package uk.co.aosd.onto.reference;

import java.time.Instant;

import uk.co.aosd.onto.foundation.Attribute;
import uk.co.aosd.onto.foundation.Individual;

/**
 * An implementation of the Attribute interface.
 *
 * @author Tony Walmsley
 */
public record AttributeImpl<I extends Individual, P>(I individual, P property, Instant from,
    Instant to) implements Attribute<I, P> {

}
