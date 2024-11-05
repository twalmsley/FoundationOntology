package uk.co.aosd.onto.signifying;

import uk.co.aosd.onto.foundation.Individual;

/**
 * A name of type V applied to some thing for a period of time.
 *
 * @author Tony Walmsley
 */
public interface Signifier<V> extends Individual {
    V name();
}
