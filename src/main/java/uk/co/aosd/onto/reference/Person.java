package uk.co.aosd.onto.reference;

import java.util.Optional;

import uk.co.aosd.onto.biological.DNA;
import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.foundation.Class;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Unit;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.signifying.Signifying;

/**
 * An example implementation of the Human interface. This may need to change.
 *
 * @author Tony Walmsley
 */
public record Person<T extends Number, U extends Unit>(
    String identifier,
    Optional<Event<T, U>> beginning,
    Optional<Event<T, U>> ending,
    Class<Signifying<T, U, String>> names,
    Language nativeLanguage,
    Class<Language> languages,
    Optional<DNA> dna) implements Human<T, U> {

}
