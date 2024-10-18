package uk.co.aosd.onto.reference;

import java.util.Optional;

import uk.co.aosd.onto.biological.DNA;
import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.foundation.Class;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.signifying.Signifying;

public record HumanPerson(
    String identifier,
    Optional<Event> beginning,
    Optional<Event> ending,
    Class<Signifying<String>> names,
    Language nativeLanguage,
    Class<Language> languages,
    Optional<DNA> dna) implements Human {

}
