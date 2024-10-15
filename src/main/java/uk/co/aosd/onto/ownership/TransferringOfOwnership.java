package uk.co.aosd.onto.ownership;

import uk.co.aosd.onto.foundation.Individual;

public interface TransferringOfOwnership extends Individual {

    Owning from();

    Owning to();
}
