package uk.co.aosd.onto.reference;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.PossibleWorld;

/**
 * An implementaton of the PossibleWorld interface.
 *
 * @author Tony Walmsley
 */
public record PossibleWorldImpl(String identifier, Set<Individual> parts, Optional<Instant> beginning,
        Optional<Instant> ending) implements PossibleWorld {

}
