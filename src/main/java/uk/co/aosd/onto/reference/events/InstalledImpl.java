package uk.co.aosd.onto.reference.events;

import java.time.Instant;

import uk.co.aosd.onto.events.Installed;

/**
 * An implementation of the Installed interface.
 *
 * @author Tony Walmsley
 */
public record InstalledImpl(String identifier, Instant from, Instant to) implements Installed {

}
