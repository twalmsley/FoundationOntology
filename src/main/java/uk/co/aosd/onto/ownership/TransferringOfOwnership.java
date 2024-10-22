package uk.co.aosd.onto.ownership;

import uk.co.aosd.onto.foundation.Activity;

public interface TransferringOfOwnership extends Activity {

    Owning from();

    Owning to();
}
