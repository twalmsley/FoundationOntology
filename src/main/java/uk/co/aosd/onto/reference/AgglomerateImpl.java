package uk.co.aosd.onto.reference;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import uk.co.aosd.onto.foundation.Agglomerate;
import uk.co.aosd.onto.foundation.Individual;

/**
 * An implementation of the Agglomerate interface.
 *
 * @author Tony Walmsley
 */
public record AgglomerateImpl(String identifier, Set<Individual> parts, Optional<Instant> beginning,
    Optional<Instant> ending) implements Agglomerate {

}
