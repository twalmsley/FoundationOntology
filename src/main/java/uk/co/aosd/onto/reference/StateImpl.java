package uk.co.aosd.onto.reference;

import java.time.Instant;
import java.util.Optional;

import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.State;

/**
 * An implementation of the State interface.
 *
 * @author Tony Walmsley
 */
public record StateImpl<V extends Individual>(String identifier, V individual, Optional<Instant> beginning,
    Optional<Instant> ending) implements State<V> {

}
