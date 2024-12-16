package uk.co.aosd.onto.signifying;

import uk.co.aosd.onto.events.Resignified;
import uk.co.aosd.onto.foundation.Class;

/**
 * A thing with one or more names signified by Strings.
 *
 * @author Tony Walmsley
 */
public interface Named<T extends Resignified> {

    Class<Signifier<String, T>> getNames();
}
