package uk.co.aosd.onto.reference;

import uk.co.aosd.onto.events.Started;
import uk.co.aosd.onto.events.Stopped;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.ownership.Owning;

/**
 * An implementation of the Owning interface.
 *
 * @author Tony Walmsley
 */
public record OwningImpl<A extends Event, B extends Event, C extends Event, D extends Event>(String identifier, String actionsDescription,
    Individual<A, B> owner, Individual<C, D> owned, Started beginning, Stopped ending) implements Owning<A, B, C, D> {

}
