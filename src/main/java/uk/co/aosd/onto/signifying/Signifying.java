package uk.co.aosd.onto.signifying;

import uk.co.aosd.onto.foundation.Activity;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;

/**
 * The activity of being named by some thing of type T for a period of time.
 */
public interface Signifying<T> extends Activity {
    T name();

    UniquelyIdentifiable named();
}
