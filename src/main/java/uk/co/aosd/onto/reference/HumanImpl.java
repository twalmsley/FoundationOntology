package uk.co.aosd.onto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HumanImpl implements Human {
    private String identifier;
    private Birth beginning;
    private Death ending;
    private Class<Signifier<String>> names;
    private Language nativeLanguage;
    private Class<Language> languages;
    private DNA dna;
}
