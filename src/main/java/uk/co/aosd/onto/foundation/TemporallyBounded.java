package uk.co.aosd.onto.foundation;

import java.time.Duration;
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

    /**
     * A default calculation of the duration between the beginning and ending
     * Instants.
     *
     * @return Duration
     */
    default Duration duration() {
        final var beginning = beginning();
        final var ending = ending();
        if (beginning.isPresent() && ending.isPresent()) {
            return Duration.between(beginning.get(), ending.get());
        }
        return Duration.ZERO;
    }
}
