package uk.co.aosd.onto.signifying;

import uk.co.aosd.onto.foundation.Activity;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;
import uk.co.aosd.onto.language.Language;

/**
 * The activity of being named by some thing of type V for a period of time.
 *
 * @author Tony Walmsley
 */
public interface Signifying<V> extends Activity {
    V name();

    UniquelyIdentifiable named();

    Language language();
}
