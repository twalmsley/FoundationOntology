package uk.co.aosd.onto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.aosd.onto.events.Resignified;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.signifying.Signifying;

/**
 * An implementation of Signifying.
 *
 * @author Tony Walmsley
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignifyingImpl<T> implements Signifying<T> {
    private String identifier;
    private String actionsDescription;
    private T name;
    private Language language;
    private UniquelyIdentifiable named;
    private Resignified beginning;
    private Resignified ending;
}
