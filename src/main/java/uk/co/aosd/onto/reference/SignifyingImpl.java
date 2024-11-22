package uk.co.aosd.onto.reference;

import uk.co.aosd.onto.events.Resignified;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.signifying.Signifying;

/**
 * An implementation of Signifying.
 *
 * @author Tony Walmsley
 */
public record SignifyingImpl<T>(String identifier, String actionsDescription, T name, Language language, UniquelyIdentifiable named,
    Resignified beginning, Resignified ending) implements Signifying<T> {
    public SignifyingImpl {
        ensureValid(beginning, ending);
    }
}
