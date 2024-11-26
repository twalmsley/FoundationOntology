package uk.co.aosd.onto.reference.events;

import java.time.Instant;

import uk.co.aosd.onto.events.Assembled;

/**
 * An implementation of an extension to the Event interface.
 *
 * @author Tony Walmsley
 */
public record AssembledImpl(String identifier, Instant from, Instant to) implements Assembled {
    public AssembledImpl {
        ensureValid(from, to);
    }

}
