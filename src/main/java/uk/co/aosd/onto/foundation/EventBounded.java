package uk.co.aosd.onto.foundation;

import java.time.Duration;
import java.util.Optional;

import uk.co.aosd.onto.util.Range;

/**
 * A thing that has a beginning and ending events, possibly unknown.
 *
 * @author Tony Walmsley
 */
public interface EventBounded<B extends Event, E extends Event> {
    B beginning();

    E ending();

    /**
     * Calculate the minimum and maximum durations between two events. The minimum
     * is the time between the end of the first event and the start of the second
     * event, while the maximum is the duration between the start of the first event
     * and the end of the second event.
     *
     * @return Optional Range
     */
    default Optional<Range<Duration>> range() {
        final var min = TimePeriod.durationBetween(beginning().to(), ending().from());
        final var max = TimePeriod.durationBetween(beginning().from(), ending().to());

        if (min.isPresent() && max.isPresent()) {
            return Optional.of(new Range<>(min.get(), max.get()));
        }
        return Optional.empty();
    }

    /**
     * Check that the from and to Instants are in the right order.
     */
    default void ensureValid(B start, E end) {
        if (start.from() != null && end.from() != null && !(start.from().isBefore(end.from()) || start.from().equals(end.from()))) {
            throw new RuntimeException("EventBounded: from must be before to.");
        }
    }

}
