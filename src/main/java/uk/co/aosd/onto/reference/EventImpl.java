package uk.co.aosd.onto.reference;

import java.time.Instant;
import java.util.Optional;

import uk.co.aosd.onto.foundation.Event;

/**
 * An implementation of the Event interface.
 *
 * @author Tony Walmsley
 */
public record EventImpl(String identifier, Optional<Instant> from, Optional<Instant> to) implements Event {

}
