package uk.co.aosd.onto.reference.events;

import java.time.Instant;

import uk.co.aosd.onto.events.Dissolved;

/**
 * An implementation of an extension to the Event interface.
 *
 * @author Tony Walmsley
 */
public record DissolvedImpl(String identifier, Instant from, Instant to) implements Dissolved {

}
