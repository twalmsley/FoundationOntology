package uk.co.aosd.onto.reference.events;

import java.time.Instant;

import uk.co.aosd.onto.events.Removed;

/**
 * An implementation of an extension to the Event interface.
 *
 * @author Tony Walmsley
 */
public record RemovedImpl(String identifier, Instant from, Instant to) implements Removed {
    public RemovedImpl {
        ensureValid(from, to);
    }

}
