package uk.co.aosd.onto.ownership;

import uk.co.aosd.onto.events.TransferredFrom;
import uk.co.aosd.onto.events.TransferredTo;
import uk.co.aosd.onto.foundation.Activity;
import uk.co.aosd.onto.foundation.Event;

/**
 * The activity of transferring ownership of something.
 *
 * @author Tony Walmsley
 */
public interface TransferringOfOwnership<A extends Event, B extends Event, C extends Event, D extends Event, T extends TransferredFrom, U extends TransferredTo>
    extends Activity<T, U> {

    Owning<A, B, C, D, T, U> getFrom();

    Owning<A, B, C, D, T, U> getTo();
}
