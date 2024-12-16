package uk.co.aosd.onto.signifying;

import uk.co.aosd.onto.events.Resignified;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.language.Language;

/**
 * A name of type V applied to some thing for a period of time.
 *
 * @author Tony Walmsley
 */
public interface Signifier<V, T extends Resignified> extends Individual<T, T> {
    V getName();

    Language getLanguage();
}
