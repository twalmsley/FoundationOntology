package uk.co.aosd.onto.reference;

import java.time.Instant;
import java.util.Optional;

import uk.co.aosd.onto.signifying.Signifier;

/**
 * An implementation of Signifier.
 *
 * @author Tony Walmsley
 */
public record SignifierImpl<T>(String identifier, T name, Optional<Instant> beginning, Optional<Instant> ending)
    implements Signifier<T> {

}
