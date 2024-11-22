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
    default Optional<Range> range() {
        final var min = TimePeriod.durationBetween(beginning().to(), ending().from());
        final var max = TimePeriod.durationBetween(beginning().from(), ending().to());

        if (min.isPresent() && max.isPresent()) {
            return Optional.of(new Range(min.get(), max.get()));
        }
        return Optional.empty();
    }

    /**
     * Represents the uncertainty over what duration an EventBounded object exists.
     *
     * @param min
     *            the minimum Duration of the object.
     * @param max
     *            the maximum Duration of the object.
     */
    public static record Range(Duration min, Duration max) {
    }
}
