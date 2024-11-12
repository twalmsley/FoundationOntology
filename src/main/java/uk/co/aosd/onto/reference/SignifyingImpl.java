package uk.co.aosd.onto.reference;

import uk.co.aosd.onto.events.Started;
import uk.co.aosd.onto.events.Stopped;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.signifying.Signifying;

/**
 * An implementation of Signifying.
 *
 * @author Tony Walmsley
 */
public record SignifyingImpl<T>(String identifier, String actionsDescription, T name, Language language, UniquelyIdentifiable named,
    Started beginning, Stopped ending) implements Signifying<T> {

}
