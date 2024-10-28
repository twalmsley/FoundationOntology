package uk.co.aosd.onto.foundation;

import java.util.Optional;

/**
 * A thing that has a beginning and an ending, possibly unknown.
 */
public interface TemporallyBounded<T extends Number, U extends Unit> {
    Optional<Event<T, U>> beginning();

    Optional<Event<T, U>> ending();
}
