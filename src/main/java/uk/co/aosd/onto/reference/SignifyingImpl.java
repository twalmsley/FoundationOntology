package uk.co.aosd.onto.reference;

import java.time.Instant;
import java.util.Optional;

import uk.co.aosd.onto.foundation.UniquelyIdentifiable;
import uk.co.aosd.onto.signifying.Signifying;

/**
 * An implementation of Signifying.
 *
 * @author Tony Walmsley
 */
public record SignifyingImpl<T>(String identifier, String actionsDescription, T name, UniquelyIdentifiable named,
    Optional<Instant> beginning, Optional<Instant> ending) implements Signifying<T> {

}
