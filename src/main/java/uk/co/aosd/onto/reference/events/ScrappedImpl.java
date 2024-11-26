package uk.co.aosd.onto.reference.events;

import java.time.Instant;

import uk.co.aosd.onto.events.Scrapped;

/**
 * An implementation of an extension to the Event interface.
 *
 * @author Tony Walmsley
 */
public record ScrappedImpl(String identifier, Instant from, Instant to) implements Scrapped {
    public ScrappedImpl {
        ensureValid(from, to);
    }

}
