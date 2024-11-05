package uk.co.aosd.onto.reference;

import uk.co.aosd.onto.language.Language;

/**
 * An implementation of Language.
 *
 * @author Tony Walmsley
 */
public record LanguageImpl(String identifier, String name) implements Language {

}
