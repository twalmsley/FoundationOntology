package uk.co.aosd.onto.person;

import uk.co.aosd.onto.biological.BiologicalEntity;
import uk.co.aosd.onto.foundation.Class;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.signifying.Named;

public interface Human extends Named, Individual, BiologicalEntity {

    Language nativeLanguage();

    Class<Language> languages();
}
