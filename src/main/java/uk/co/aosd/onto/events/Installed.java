package uk.co.aosd.onto.events;

import java.time.Instant;

import uk.co.aosd.onto.foundation.Event;

/**
 * An implementation of the Installed interface.
 *
 * @author Tony Walmsley
 */
public record Installed(String identifier, Instant from, Instant to) implements Event {

}
