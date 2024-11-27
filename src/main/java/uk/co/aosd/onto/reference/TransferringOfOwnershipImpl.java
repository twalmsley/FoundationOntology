package uk.co.aosd.onto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.aosd.onto.events.TransferredFrom;
import uk.co.aosd.onto.events.TransferredTo;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.ownership.Owning;
import uk.co.aosd.onto.ownership.TransferringOfOwnership;

/**
 * An implementation of the TransferringOfOwnership interface.
 *
 * @author Tony Walmsley
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferringOfOwnershipImpl<A extends Event, B extends Event, C extends Event, D extends Event> implements TransferringOfOwnership<A, B, C, D> {
    private String identifier;
    private String actionsDescription;
    private Owning<A, B, C, D> from;
    private Owning<A, B, C, D> to;
    private TransferredFrom beginning;
    private TransferredTo ending;
}
