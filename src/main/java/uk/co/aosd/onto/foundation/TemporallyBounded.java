package uk.co.aosd.onto.foundation;

import java.util.Optional;

public interface TemporallyBounded {
    Optional<Event> beginning();

    Optional<Event> ending();
}
