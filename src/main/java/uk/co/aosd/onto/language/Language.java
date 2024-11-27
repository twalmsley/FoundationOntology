package uk.co.aosd.onto.language;

import uk.co.aosd.onto.foundation.UniquelyIdentifiable;

/**
 * A language used for communication, which could be a human language, computer
 * language, or any other language.
 *
 * @author Tony Walmsley
 */
public interface Language extends UniquelyIdentifiable {

    String getName();
}
