package uk.co.aosd.onto.reference.events;

import java.time.Instant;

import uk.co.aosd.onto.events.TransferredFrom;

/**
 * An implementation of an extension to the Event interface.
 *
 * @author Tony Walmsley
 */
public record TransferredFromImpl(String identifier, Instant from, Instant to) implements TransferredFrom {
    public TransferredFromImpl {
        ensureValid(from, to);
    }

}
