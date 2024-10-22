package uk.co.aosd.onto.ownership;

import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.temporal.Event;

public interface OwnershipServices {

    Owning createOwning(Individual owned, Individual owner, Event when);

    TransferringOfOwnership transfer(Owning from, Individual newOwner, Event when);
}
