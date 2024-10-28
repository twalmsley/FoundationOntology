package uk.co.aosd.onto.ownership;

import uk.co.aosd.onto.foundation.Activity;
import uk.co.aosd.onto.foundation.Unit;

/**
 * The activity of transferring ownership of something.
 */
public interface TransferringOfOwnership<T extends Number, U extends Unit> extends Activity<T, U> {

    Owning<T, U> from();

    Owning<T, U> to();
}
