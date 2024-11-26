package uk.co.aosd.onto.reference.events;

import java.time.Instant;

import uk.co.aosd.onto.events.Resignified;

/**
 * An implementation of an extension to the Event interface.
 *
 * @author Tony Walmsley
 */
public record ResignifiedImpl(String identifier, Instant from, Instant to) implements Resignified {
    public ResignifiedImpl {
        ensureValid(from, to);
    }

}
