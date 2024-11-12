package uk.co.aosd.onto.ownership;

import uk.co.aosd.onto.foundation.Activity;
import uk.co.aosd.onto.foundation.Event;

/**
 * The activity of transferring ownership of something.
 *
 * @author Tony Walmsley
 */
public interface TransferringOfOwnership<A extends Event, B extends Event, C extends Event, D extends Event> extends Activity {

    Owning<A, B, C, D> from();

    Owning<A, B, C, D> to();
}
