package uk.co.aosd.onto.signifying;

import uk.co.aosd.onto.foundation.Class;
import uk.co.aosd.onto.foundation.Unit;

/**
 * A thing with one or more names signified by Strings.
 *
 * @author Tony Walmsley
 */
public interface Named<T extends Number, U extends Unit> {

    Class<Signifying<T, U, String>> names();
}
