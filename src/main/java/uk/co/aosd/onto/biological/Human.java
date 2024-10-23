package uk.co.aosd.onto.biological;

import uk.co.aosd.onto.foundation.Class;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.signifying.Named;

/**
 * Distinguishing characteristics of Humans.
 */
public interface Human extends Named, GenomicBiologicalEntity {

    Language nativeLanguage();

    Class<Language> languages();
}
