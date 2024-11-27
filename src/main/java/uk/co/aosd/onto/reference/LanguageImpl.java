package uk.co.aosd.onto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.aosd.onto.language.Language;

/**
 * An implementation of Language.
 *
 * @author Tony Walmsley
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageImpl implements Language {
    private String identifier;
    private String name;
}
