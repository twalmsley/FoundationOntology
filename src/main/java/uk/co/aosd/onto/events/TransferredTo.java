package uk.co.aosd.onto.events;

import java.time.Instant;

import uk.co.aosd.onto.foundation.Event;

/**
 * An implementation of an extension to the Event interface.
 *
 * @author Tony Walmsley
 */
public record TransferredTo(String identifier, Instant from, Instant to) implements Event {
    public TransferredTo {
        ensureValid(from, to);
    }

}
