package uk.co.aosd.onto.foundation;

import java.time.Instant;
import java.util.Optional;

/**
 * A thing that has a beginning and an ending, possibly unknown.
 *
 * @author Tony Walmsley
 */
public interface TemporallyBounded {
    Optional<Instant> beginning();

    Optional<Instant> ending();
}
