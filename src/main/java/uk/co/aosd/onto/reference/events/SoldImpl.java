package uk.co.aosd.onto.reference.events;

import java.time.Instant;

import uk.co.aosd.onto.events.Sold;

/**
 * An implementation of an extension to the Event interface.
 *
 * @author Tony Walmsley
 */
public record SoldImpl(String identifier, Instant from, Instant to) implements Sold {
    public SoldImpl {
        ensureValid(from, to);
    }

}
