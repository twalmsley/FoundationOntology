package uk.co.aosd.onto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.aosd.onto.events.Resignified;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.signifying.Signifier;

/**
 * An implementation of Signifier.
 *
 * @author Tony Walmsley
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignifierImpl<T> implements Signifier<T> {
    private String identifier;
    private T name;
    private Language language;
    private Resignified beginning;
    private Resignified ending;
}
