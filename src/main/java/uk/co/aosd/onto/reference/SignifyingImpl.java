package uk.co.aosd.onto.reference;

import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;
import uk.co.aosd.onto.signifying.Signifying;

/**
 * An implementation of Signifying.
 *
 * @author Tony Walmsley
 */
public record SignifyingImpl<T>(String identifier, String actionsDescription, T name, UniquelyIdentifiable named,
    Event beginning, Event ending) implements Signifying<T> {

}
