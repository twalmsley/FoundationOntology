package uk.co.aosd.onto.ownership;

import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;

public interface OwnershipServices {

    TransferringOfOwnership transfer(Owning from, Individual to, Event when);
}
