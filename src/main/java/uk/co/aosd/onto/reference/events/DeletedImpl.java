package uk.co.aosd.onto.reference.events;

import java.time.Instant;

import uk.co.aosd.onto.events.Deleted;

/**
 * An implementation of an extension to the Event interface.
 *
 * @author Tony Walmsley
 */
public record DeletedImpl(String identifier, Instant from, Instant to) implements Deleted {
    public DeletedImpl {
        ensureValid(from, to);
    }

}
