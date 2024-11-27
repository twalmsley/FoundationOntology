package uk.co.aosd.onto.foundation;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

/**
 * A thing that has a beginning and an ending in the time dimension, possibly
 * unknown.
 *
 * @author Tony Walmsley
 */
public interface TimePeriod {
    Instant getFrom();

    Instant getTo();

    /**
     * A default calculation of the duration between the beginning and ending
     * Instants.
     *
     * @return Duration
     */
    default Optional<Duration> duration() {
        return durationBetween(getFrom(), getTo());
    }

    /**
     * Check that the from and to Instants are in the right order.
     */
    default void ensureValid(Instant from, Instant to) {
        if (from != null && to != null && !(from.isBefore(to) || from.equals(to))) {
            throw new RuntimeException("TimePeriod: from must be before to.");
        }
    }

    /**
     * A default calculation of the duration between the beginning and ending
     * Instants.
     *
     * @param beginning
     *            Instant
     * @param ending
     *            Instant
     * @return Duration
     */
    static Optional<Duration> durationBetween(final Instant beginning, final Instant ending) {
        if (beginning != null && ending != null) {
            return Optional.of(Duration.between(beginning, ending));
        }
        return Optional.empty();
    }
}
