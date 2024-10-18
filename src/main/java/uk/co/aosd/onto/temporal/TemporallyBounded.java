package uk.co.aosd.onto.temporal;

import java.util.Optional;

public interface TemporallyBounded {
    Optional<Event> beginning();

    Optional<Event> ending();
}
