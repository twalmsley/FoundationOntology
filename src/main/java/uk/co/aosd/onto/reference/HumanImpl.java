package uk.co.aosd.onto.reference;

import uk.co.aosd.onto.biological.DNA;
import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.events.Birth;
import uk.co.aosd.onto.events.Death;
import uk.co.aosd.onto.foundation.Class;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.signifying.Signifier;

/**
 * An example implementation of the Human interface. This may need to change.
 *
 * @author Tony Walmsley
 */
public record HumanImpl(
    String identifier,
    Birth beginning,
    Death ending,
    Class<Signifier<String>> names,
    Language nativeLanguage,
    Class<Language> languages,
    DNA dna) implements Human {

}
