package uk.co.aosd.onto.reference.events;

import java.time.Instant;

import uk.co.aosd.onto.events.Bought;

/**
 * An implementation of an extension to the Event interface.
 *
 * @author Tony Walmsley
 */
public record BoughtImpl(String identifier, Instant from, Instant to) implements Bought {
    public BoughtImpl {
        ensureValid(from, to);
    }

}
