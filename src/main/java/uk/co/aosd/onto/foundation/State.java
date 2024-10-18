package uk.co.aosd.onto.foundation;

import uk.co.aosd.onto.temporal.TemporallyBounded;

public interface State<T extends UniquelyIdentifiable> extends TemporallyBounded, UniquelyIdentifiable {
    T individual();
}
