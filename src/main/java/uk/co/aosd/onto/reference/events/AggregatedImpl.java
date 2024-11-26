package uk.co.aosd.onto.reference.events;

import java.time.Instant;

import uk.co.aosd.onto.events.Aggregated;

/**
 * An implementation of an extension to the Event interface.
 *
 * @author Tony Walmsley
 */
public record AggregatedImpl(String identifier, Instant from, Instant to) implements Aggregated {
    public AggregatedImpl {
        ensureValid(from, to);
    }
}
