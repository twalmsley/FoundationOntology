package uk.co.aosd.onto.ownership;

import uk.co.aosd.onto.events.TransferredFrom;
import uk.co.aosd.onto.events.TransferredTo;
import uk.co.aosd.onto.foundation.Activity;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;

/**
 * The activity of owning something.
 *
 * @author Tony Walmsley
 */
public interface Owning<A extends Event, B extends Event, C extends Event, D extends Event, T extends TransferredFrom, U extends TransferredTo>
    extends Activity<T, U> {

    Individual<A, B> getOwner();

    Individual<C, D> getOwned();
}
