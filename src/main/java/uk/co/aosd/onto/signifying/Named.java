package uk.co.aosd.onto.signifying;

import uk.co.aosd.onto.foundation.Class;

/**
 * A thing with one or more names signified by Strings.
 */
public interface Named {

    Class<Signifying<String>> names();
}
