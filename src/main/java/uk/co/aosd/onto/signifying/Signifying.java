package uk.co.aosd.onto.signifying;

import uk.co.aosd.onto.foundation.UniquelyIdentifiable;
import uk.co.aosd.onto.temporal.TemporallyBounded;

public interface Signifying<T> extends TemporallyBounded, UniquelyIdentifiable {
    T name();

    UniquelyIdentifiable named();
}
