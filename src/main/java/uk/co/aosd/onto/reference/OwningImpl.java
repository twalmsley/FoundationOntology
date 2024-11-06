package uk.co.aosd.onto.reference;

import java.time.Instant;
import java.util.Optional;

import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.ownership.Owning;

/**
 * An implementation of the Owning interface.
 *
 * @author Tony Walmsley
 */
public record OwningImpl(String identifier, String actionsDescription, Individual owner, Individual owned,
    Optional<Instant> beginning, Optional<Instant> ending) implements Owning {

}
