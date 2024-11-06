package uk.co.aosd.onto.reference;

import java.time.Instant;
import java.util.Optional;

import uk.co.aosd.onto.foundation.Individual;

/**
 * An implementation of the Individual interface.
 *
 * @author Tony Walmsley
 */
public record IndividualImpl(String identifier, Optional<Instant> beginning, Optional<Instant> ending) implements Individual {
}