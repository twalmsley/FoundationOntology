package uk.co.aosd.onto.temporal;

import java.util.Optional;

import uk.co.aosd.onto.foundation.Event;

public interface TemporallyBounded {
    Optional<Event> beginning();

    Optional<Event> ending();
}
