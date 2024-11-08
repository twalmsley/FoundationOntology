package uk.co.aosd.onto.foundation;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

/**
 * A thing that has a beginning and an ending in the time dimension, possibly unknown.
 *
 * @author Tony Walmsley
 */
public interface TimePeriod {
    Optional<Instant> from();

    Optional<Instant> to();

    /**
     * A default calculation of the duration between the beginning and ending
     * Instants.
     *
     * @return Duration
     */
    default Optional<Duration> duration() {
        final var beginning = from();
        final var ending = to();
        if (beginning.isPresent() && ending.isPresent()) {
            return Optional.of(Duration.between(beginning.get(), ending.get()));
        }
        return Optional.empty();
    }
}
