package uk.co.aosd.onto.signifying;

import uk.co.aosd.onto.foundation.TemporallyBounded;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;

public interface Signifying<T> extends TemporallyBounded, UniquelyIdentifiable {
    T name();

    UniquelyIdentifiable named();
}
