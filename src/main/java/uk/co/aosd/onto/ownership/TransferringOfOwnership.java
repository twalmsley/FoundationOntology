package uk.co.aosd.onto.ownership;

import uk.co.aosd.onto.foundation.Activity;

/**
 * The activity of transferring ownership of something.
 */
public interface TransferringOfOwnership extends Activity {

    Owning from();

    Owning to();
}
