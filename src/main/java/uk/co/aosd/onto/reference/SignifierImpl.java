package uk.co.aosd.onto.reference;

import uk.co.aosd.onto.events.Resignified;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.signifying.Signifier;

/**
 * An implementation of Signifier.
 *
 * @author Tony Walmsley
 */
public record SignifierImpl<T>(String identifier, T name, Language language, Resignified beginning, Resignified ending)
    implements Signifier<T> {
    public SignifierImpl {
        ensureValid(beginning, ending);
    }
}
