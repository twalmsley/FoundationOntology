package uk.co.aosd.onto.reference.events;

import java.time.Instant;

import uk.co.aosd.onto.events.Changed;

/**
 * An implementation of an extension to the Event interface.
 *
 * @author Tony Walmsley
 */
public record ChangedImpl(String identifier, Instant from, Instant to) implements Changed {

}
