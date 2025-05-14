package uk.co.aosd.onto.foundation;

import java.time.Duration;
import java.util.Optional;

import uk.co.aosd.onto.util.Range;

/**
 * A thing that has a beginning and ending events, possibly unknown.
 * 
 * <p>EventBounded represents entities that exist between two events - a beginning
 * event and an ending event. This interface is foundational for modeling entities
 * with temporal existence in the ontology.</p>
 * 
 * <p>Unlike {@link TimePeriod} which directly uses time points, EventBounded uses
 * events to mark temporal boundaries. This is more flexible and semantically richer,
 * as events can carry additional information about how and why the entity came into
 * or went out of existence.</p>
 * 
 * <p>The interface provides methods to:</p>
 * <ul>
 *   <li>Access the beginning and ending events</li>
 *   <li>Calculate duration ranges between events</li>
 *   <li>Validate temporal consistency</li>
 * </ul>
 * 
 * <p>The type parameters B and E specify the types of events that can serve as
 * beginning and ending events, allowing for type-safe modeling of different
 * kinds of event-bounded entities.</p>
 * 
 * @param <B> The type of event that marks the beginning of this entity
 * @param <E> The type of event that marks the ending of this entity
 * 
 * @author Tony Walmsley
 * @see Event
 * @see TimePeriod
 * @see Range
 */
public interface EventBounded<B extends Event, E extends Event> {
    /**
     * Gets the event that marks the beginning of this entity.
     * 
     * @return the beginning event
     */
    B getBeginning();

    /**
     * Gets the event that marks the ending of this entity.
     * 
     * @return the ending event
     */
    E getEnding();

    /**
     * Calculate the minimum and maximum durations between the beginning and ending events.
     * 
     * <p>This method accounts for the fact that events themselves occur over time periods,
     * so the actual duration of the entity's existence has a minimum and maximum value:</p>
     * <ul>
     *   <li>The minimum duration is calculated from the end of the beginning event to the start of the ending event</li>
     *   <li>The maximum duration is calculated from the start of the beginning event to the end of the ending event</li>
     * </ul>
     * 
     * <p>If any of the required time points are unknown, an empty Optional is returned.</p>
     *
     * @return an Optional containing a Range with minimum and maximum durations,
     *         or an empty Optional if any required time point is unknown
     * @see Range
     * @see TimePeriod#durationBetween(Instant, Instant)
     */
    default Optional<Range<Duration>> range() {
        final var min = TimePeriod.durationBetween(getBeginning().getTo(), getEnding().getFrom());
        final var max = TimePeriod.durationBetween(getBeginning().getFrom(), getEnding().getTo());

        if (min.isPresent() && max.isPresent()) {
            return Optional.of(new Range<>(min.get(), max.get()));
        }
        return Optional.empty();
    }

    /**
     * Validates that the temporal order of events is consistent.
     * 
     * <p>This method checks that the beginning event starts before or at the same time
     * as the ending event. It should be called during construction of EventBounded
     * implementations to ensure temporal consistency.</p>
     * 
     * @param start the beginning event to validate
     * @param end the ending event to validate
     * @throws RuntimeException if the beginning event starts after the ending event
     */
    default void ensureValid(B start, E end) {
        if (start.getFrom() != null && end.getFrom() != null && !(start.getFrom().isBefore(end.getFrom()) || start.getFrom().equals(end.getFrom()))) {
            throw new RuntimeException("EventBounded: from must be before to.");
        }
    }
}
