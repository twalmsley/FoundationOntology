package uk.co.aosd.onto.reference;

import uk.co.aosd.onto.events.Started;
import uk.co.aosd.onto.events.Stopped;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.ownership.Owning;
import uk.co.aosd.onto.ownership.TransferringOfOwnership;

/**
 * An implementation of the TransferringOfOwnership interface.
 *
 * @author Tony Walmsley
 */
public record TransferringOfOwnershipImpl<A extends Event, B extends Event, C extends Event, D extends Event>(String identifier, String actionsDescription,
    Owning<A, B, C, D> from, Owning<A, B, C, D> to,
    Started beginning, Stopped ending) implements TransferringOfOwnership<A, B, C, D> {

}
