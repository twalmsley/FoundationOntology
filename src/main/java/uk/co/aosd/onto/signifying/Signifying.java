package uk.co.aosd.onto.signifying;

import uk.co.aosd.onto.foundation.Activity;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;
import uk.co.aosd.onto.foundation.Unit;

/**
 * The activity of being named by some thing of type V for a period of time.
 *
 * @author Tony Walmsley
 */
public interface Signifying<T extends Number, U extends Unit, V> extends Activity<T, U> {
    V name();

    UniquelyIdentifiable named();
}
