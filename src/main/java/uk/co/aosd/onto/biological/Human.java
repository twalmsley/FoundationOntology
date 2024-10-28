package uk.co.aosd.onto.biological;

import uk.co.aosd.onto.foundation.Class;
import uk.co.aosd.onto.foundation.Unit;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.signifying.Named;

/**
 * Distinguishing characteristics of Humans.
 */
public interface Human<T extends Number, U extends Unit> extends Named<T, U>, GenomicBiologicalEntity<T, U> {

    Language nativeLanguage();

    Class<Language> languages();
}
