package uk.co.aosd.onto.reference;

import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.signifying.Signifier;

/**
 * An implementation of Signifier.
 *
 * @author Tony Walmsley
 */
public record SignifierImpl<T>(String identifier, T name, Event beginning, Event ending)
    implements Signifier<T> {

}
