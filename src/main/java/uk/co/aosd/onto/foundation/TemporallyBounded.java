package uk.co.aosd.onto.foundation;

import java.util.Optional;

/**
 * A thing that has a beginning and an ending, possibly unknown.
 *
 * @author Tony Walmsley
 */
public interface TemporallyBounded {
    Optional<Event> beginning();

    Optional<Event> ending();
}
