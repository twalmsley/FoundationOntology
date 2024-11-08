package uk.co.aosd.onto.reference;

import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.ownership.Owning;
import uk.co.aosd.onto.ownership.TransferringOfOwnership;

/**
 * An implementation of the TransferringOfOwnership interface.
 *
 * @author Tony Walmsley
 */
public record TransferringOfOwnershipImpl(String identifier, String actionsDescription, Owning from, Owning to,
    Event beginning, Event ending) implements TransferringOfOwnership {

}
