package uk.co.aosd.onto.signifying;

import uk.co.aosd.onto.foundation.Activity;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;

public interface Signifying<T> extends Activity {
    T name();

    UniquelyIdentifiable named();
}
