package uk.co.aosd.onto.reference.events;

import java.time.Instant;

import uk.co.aosd.onto.events.Formed;

/**
 * An implementation of an extension to the Event interface.
 *
 * @author Tony Walmsley
 */
public record FormedImpl(String identifier, Instant from, Instant to) implements Formed {

}
