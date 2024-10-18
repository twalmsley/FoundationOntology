package uk.co.aosd.onto.ownership;

import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.temporal.Event;

public interface OwnershipServices {

    TransferringOfOwnership transfer(Owning from, Individual to, Event when);
}
