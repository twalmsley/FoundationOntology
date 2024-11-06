package uk.co.aosd.onto.reference;

import java.time.Instant;
import java.util.Optional;

import uk.co.aosd.onto.ownership.Owning;
import uk.co.aosd.onto.ownership.TransferringOfOwnership;

/**
 * An implementation of the TransferringOfOwnership interface.
 *
 * @author Tony Walmsley
 */
public record TransferringOfOwnershipImpl(String identifier, String actionsDescription, Owning from, Owning to,
    Optional<Instant> beginning, Optional<Instant> ending) implements TransferringOfOwnership {

}
