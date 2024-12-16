package uk.co.aosd.onto.biological;

import uk.co.aosd.onto.events.Birth;
import uk.co.aosd.onto.events.Death;
import uk.co.aosd.onto.events.Resignified;
import uk.co.aosd.onto.foundation.Class;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.signifying.Named;

/**
 * Distinguishing characteristics of Humans.
 *
 * @author Tony Walmsley
 */
public interface Human<T extends Birth, U extends Death, V extends Resignified, L extends Language> extends Named<V>, GenomicBiologicalEntity<T, U> {

    L getNativeLanguage();

    Class<L> getLanguages();
}
