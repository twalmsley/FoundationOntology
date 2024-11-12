package uk.co.aosd.onto.reference.events;

import java.time.Instant;

import uk.co.aosd.onto.events.Destroyed;

/**
 * An implementation of an extension to the Event interface.
 *
 * @author Tony Walmsley
 */
public record DestroyedImpl(String identifier, Instant from, Instant to) implements Destroyed {

}
