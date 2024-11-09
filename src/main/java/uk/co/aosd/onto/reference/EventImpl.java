package uk.co.aosd.onto.reference;

import java.time.Instant;

import uk.co.aosd.onto.foundation.Event;

/**
 * An implementation of the Event interface.
 *
 * @author Tony Walmsley
 */
public record EventImpl(String identifier, Instant from, Instant to) implements Event {

}
