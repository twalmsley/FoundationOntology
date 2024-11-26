package uk.co.aosd.onto.reference.events;

import java.time.Instant;

import uk.co.aosd.onto.events.Created;

/**
 * An implementation of an extension to the Event interface.
 *
 * @author Tony Walmsley
 */
public record CreatedImpl(String identifier, Instant from, Instant to) implements Created {
    public CreatedImpl {
        ensureValid(from, to);
    }

}
