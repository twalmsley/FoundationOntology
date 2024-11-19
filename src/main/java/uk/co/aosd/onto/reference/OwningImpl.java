package uk.co.aosd.onto.reference;

import com.fasterxml.jackson.annotation.JsonTypeName;
import uk.co.aosd.onto.events.TransferredFrom;
import uk.co.aosd.onto.events.TransferredTo;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.ownership.Owning;

/**
 * An implementation of the Owning interface.
 *
 * @author Tony Walmsley
 */
@JsonTypeName("uk.co.aosd.onto.reference.owning")
public record OwningImpl<A extends Event, B extends Event, C extends Event, D extends Event>(String identifier, String actionsDescription,
    Individual<A, B> owner, Individual<C, D> owned, TransferredFrom beginning, TransferredTo ending) implements Owning<A, B, C, D> {

}
