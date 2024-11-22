package uk.co.aosd.onto.foundation;

import java.time.Duration;
import java.util.Optional;

/**
 * A thing that has a beginning and ending events, possibly unknown.
 *
 * @author Tony Walmsley
 */
public interface EventBounded<B extends Event, E extends Event> {
    B beginning();

    E ending();

    /**
     * Calculate the minimum and maximum durations between two events.
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
}
