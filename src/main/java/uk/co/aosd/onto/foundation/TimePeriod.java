package uk.co.aosd.onto.foundation;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

/**
 * A thing that has a beginning and an ending in the time dimension, possibly
 * unknown.
 * 
 * <p>TimePeriod is a fundamental interface that represents temporal aspects of entities.
 * It defines a span of time with a beginning (from) and an ending (to) point.
 * Either or both time points may be unknown (represented as null), allowing for 
 * partially specified time periods.</p>
 * 
 * <p>TimePeriod is used throughout the ontology to model temporal boundaries.
 * It provides functionality to:</p>
 * <ul>
 *   <li>Access the beginning and ending time points</li>
 *   <li>Calculate the duration between these time points</li>
 *   <li>Validate that time points are in the correct order</li>
 * </ul>
 * 
 * <p>Implementations must ensure that the beginning time is not after the ending time
 * (when both are specified).</p>
 *
 * @author Tony Walmsley
 */
public interface TimePeriod {
    /**
     * Gets the beginning time point of this time period.
     * 
     * @return the beginning Instant, or null if unknown
     */
    Instant getFrom();

    /**
     * Gets the ending time point of this time period.
     * 
     * @return the ending Instant, or null if unknown
     */
    Instant getTo();

    /**
     * Calculates the duration between the beginning and ending
     * time points of this time period.
     * 
     * <p>If either time point is unknown (null), the duration
     * cannot be calculated and an empty Optional is returned.</p>
     *
     * @return an Optional containing the Duration between the time points,
     *         or an empty Optional if either time point is unknown
     */
    default Optional<Duration> duration() {
        return durationBetween(getFrom(), getTo());
    }

    /**
     * Validates that the beginning time is not after the ending time.
     * 
     * <p>This method should be called during construction of TimePeriod
     * implementations to ensure temporal consistency.</p>
     * 
     * @param from the beginning Instant to validate
     * @param to the ending Instant to validate
     * @throws RuntimeException if from is after to
     */
    default void ensureValid(Instant from, Instant to) {
        if (from != null && to != null && !(from.isBefore(to) || from.equals(to))) {
            throw new RuntimeException("TimePeriod: from must be before to.");
        }
    }

    /**
     * Calculates the duration between two time points.
     * 
     * <p>This is a utility method that can be used to calculate durations
     * between any two time points, not just those defining a TimePeriod.</p>
     * 
     * <p>If either time point is unknown (null), the duration
     * cannot be calculated and an empty Optional is returned.</p>
     *
     * @param beginning the starting Instant
     * @param ending the ending Instant
     * @return an Optional containing the Duration between the time points,
     *         or an empty Optional if either time point is unknown
     */
    static Optional<Duration> durationBetween(final Instant beginning, final Instant ending) {
        if (beginning != null && ending != null) {
            return Optional.of(Duration.between(beginning, ending));
        }
        return Optional.empty();
    }
}
